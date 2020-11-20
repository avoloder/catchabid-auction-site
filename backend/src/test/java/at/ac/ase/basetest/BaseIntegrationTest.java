package at.ac.ase.basetest;

import at.ac.ase.AuctionApplication;
import org.apache.commons.io.IOUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

/**
 * Base Spring Boot Integration Test which makes it possible to
 * use dependency injection and database utils
 */

@AutoConfigureTestEntityManager
@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = AuctionApplication.class)

public abstract class BaseIntegrationTest {

    @Autowired
    protected TestEntityManager testEntityManager;

    @Autowired
    protected TransactionTemplate transactionTemplate;

    /**
     * insert data from rousources folder "testdata"
     * in a separate transaction
     * @param filename in "testdata"
     */
    protected void insertTestData(String filename) {
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("testdata/" + filename)) {

            String content = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            tx(status -> {
                testEntityManager
                        .getEntityManager()
                        .createNativeQuery(content)
                        .executeUpdate();
            });

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("couldnt load contents of file " + filename);
        }
    }

    /**
     * cleans all data in all tables
     * in separate transaction
     */
    protected void cleanDatabase() {
        tx(status -> {
            executeSql("DELETE FROM Notification");
            executeSql("DELETE FROM Rating");
            executeSql("DELETE FROM Bid");
            executeSql("DELETE FROM AuctionPost");
            executeSql("DELETE FROM AuctionHouse");
            executeSql("DELETE FROM RegularUser");
            executeSql("DELETE FROM Location");
        });
    }

    /**
     * short callback function wrapper for better readability
     * example: tx(s -> doInTx());
     */
    protected void tx(Consumer<TransactionStatus> action) {
        transactionTemplate.executeWithoutResult(action);
    }

    /**
     * short callback function wrapper with result for better readability
     * example: var = txGetResult(s -> doInTx());
     */
    protected <T> T txGetResult(TransactionCallback<T> action) {
        return transactionTemplate.execute(action);
    }

    /**
     * execute plain sql
     * @param sql script to execute
     */
    protected void executeSql(String sql) {
        getEntityManager().createQuery(sql).executeUpdate();
    }

    protected EntityManager getEntityManager() {
        return testEntityManager.getEntityManager();
    }
}
