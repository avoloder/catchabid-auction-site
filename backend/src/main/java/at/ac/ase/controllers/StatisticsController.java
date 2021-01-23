package at.ac.ase.controllers;

import at.ac.ase.entities.User;
import at.ac.ase.service.statistics.implementation.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatisticsController {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    @Autowired
    StatisticsService service;

    @GetMapping("bidStatistics")
    public ResponseEntity<Map<String,Integer>> bidStatistics( @CurrentSecurityContext(expression = "authentication.principal")User user) {
        logger.info("Get bid statistics for user with email " + user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(service.getBidsStatistics(user));
    }

    @GetMapping("winStatistics")
    public ResponseEntity<Map<String,Integer>> winsStatistics( @CurrentSecurityContext(expression = "authentication.principal")User user) {
        logger.info("Get bid statistics for user with email " + user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(service.getWinsStatistics(user));
    }
    @GetMapping("winBidRatio")
    public ResponseEntity<Map<String,Double>> winBidRatio( @CurrentSecurityContext(expression = "authentication.principal")User user) {
        logger.info("Get bid statistics for user with email " + user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(service.getBidsWinsRatio(user));
    }
    @GetMapping("myAuctionsPopularity")
    public ResponseEntity<Map<String,Integer>> popularityOfAuctions( @CurrentSecurityContext(expression = "authentication.principal")User user) {
        logger.info("Get bid statistics for user with email " + user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(service.getPopularityOfOwnAuctions(user));
    }
    @GetMapping("myAuctionsSuccess")
    public ResponseEntity<Map<String,Double>> successOfAuctions( @CurrentSecurityContext(expression = "authentication.principal")User user) {
        logger.info("Get bid statistics for user with email " + user.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(service.getSuccessOfMyAuctions(user));
    }

}
