package at.ac.ase.e2e;

import at.ac.ase.basetest.BaseE2E;
import at.ac.ase.e2e.pages.CatchabidBasePage;
import at.ac.ase.postgres.auction.AuctionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class F12_ProjectSetupE2E extends BaseE2E {

    @Autowired
    AuctionRepository auctionRepository;

    @Test
    void testApplicationContext() {
        assertNotNull(auctionRepository);
    }

    @Test
    void testDbAccess() {
        insertTestData("F12_tc1.sql");
        assertEquals(3L, auctionRepository.findAll().size());
        cleanDatabase();
    }

    @Test
    void testBaseLayout() {
        navigateToCatchabidPage();

        CatchabidBasePage catchabidBasePage = getCatchabidPage();

        assertThat(catchabidBasePage.getTitle(), is("Catchabid"));
        assertThat(catchabidBasePage.getFooterCopyrightLink().getText(), is("© 2020 ASE WS 2020 Group 6"));
    }
}
