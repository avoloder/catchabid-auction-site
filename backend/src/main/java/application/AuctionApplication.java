package application;

import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories
@EnableJpaRepositories
public class AuctionApplication {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(AuctionApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuctionApplication.class, args);
        logger.info("Start up application");
    }

}
