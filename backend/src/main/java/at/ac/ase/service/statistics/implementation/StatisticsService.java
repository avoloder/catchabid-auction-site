package at.ac.ase.service.statistics.implementation;

import at.ac.ase.controllers.AuctionController;
import at.ac.ase.entities.*;
import at.ac.ase.service.auction.implementation.AuctionService;
import at.ac.ase.service.bid.implementation.BidService;
import at.ac.ase.service.statistics.IStatisticsService;
import at.ac.ase.service.user.implementation.AuctionHouseService;
import at.ac.ase.service.user.implementation.RegularUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StatisticsService implements IStatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(AuctionController.class);
    @Autowired
    RegularUserService regularUserService;

    @Autowired
    AuctionHouseService auctionHouseService;

    @Autowired
    BidService bidService;

    @Autowired
    AuctionService auctionService;


    @Override
    public Map<String, Integer> getBidsStatistics(User user) {
        User retrievedUser = getUser(user);
        if (retrievedUser == null) {
            return new HashMap<>();
        }
        Map<String, Integer> statistics = new HashMap<>();
        Set<Bid> bids = retrievedUser.getBids();

        bids.forEach(x -> addStatistics(statistics, x));

        return statistics;
    }

    private void addStatistics(Map<String, Integer> statistics, Bid bid) {

        Integer count = statistics.get(bid.getAuction().getCategory().name());
        if (count == null) {
            statistics.put(bid.getAuction().getCategory().name(), Integer.valueOf(1));
        } else {
            statistics.put(bid.getAuction().getCategory().name(), Integer.valueOf(count.intValue() + 1));
        }
    }

    @Override
    public Map<String, Integer> getWinsStatistics(User user) {
        User retrievedUser = getUser(user);
        if (retrievedUser == null) {
            return new HashMap<>();
        }

        Map<String, Integer> statistics = new HashMap<>();
        Set<Bid> bids = retrievedUser.getBids();

        Stream<Bid> wins = bids.stream().filter(x -> x.getId() == x.getAuction().getHighestBid().getId() && x.getAuction().getEndTime().isBefore(LocalDateTime.now()));

        wins.forEach(x -> addStatistics(statistics, x));

        return statistics;
    }

    @Override
    public Map<String, Integer> getBidsWinsRatio(User user) {
        User retrievedUser = regularUserService.getUserByEmail(user.getEmail());
        if (retrievedUser == null) {
            return new HashMap<>();
        }
        Map<String, Integer> statistics = new HashMap<>();
        Set<Bid> bids = retrievedUser.getBids();

        Stream<Bid> wins = bids.stream().filter(x -> x.getId().equals(x.getAuction().getHighestBid().getId()) && x.getAuction().getEndTime().isBefore(LocalDateTime.now()));
        int winsCount = wins.toArray().length;
        statistics.put("wins", winsCount);
        int lossCount = bids.stream().collect(Collectors.groupingBy(Bid::getAuction)).keySet().size() - winsCount;
        statistics.put("loss", lossCount);
        return statistics;
    }

    @Override
    public Map<String, Integer> getPopularityOfOwnAuctions(User user) {
        User retrievedUser = getUser(user);
        if (retrievedUser == null) {
            return new HashMap<>();
        }
        Set<AuctionPost> myPosts = retrievedUser.getOwnedAuctions();
        Map<Category, List<AuctionPost>> auctionsByCategory = myPosts.stream().collect(Collectors.groupingBy(AuctionPost::getCategory));
        Map<String, Integer> auctionsPopularityByCategory = new HashMap<>();
        for (Category k : auctionsByCategory.keySet()) {
            List<AuctionPost> posts = auctionsByCategory.get(k);
            int count= posts.stream().mapToInt(x -> bidService.getAuctionBids(x).size()).sum();
            auctionsPopularityByCategory.put(k.name(),count/posts.size());
        }

        return auctionsPopularityByCategory;
    }

    @Override
    public Map<String, Double> getSuccessOfMyAuctions(User user) {
        User retrievedUser = getUser(user);
        if (retrievedUser == null) {
            return new HashMap<>();
        }
        Set<AuctionPost> myPosts = retrievedUser.getOwnedAuctions();
        Map<Category, List<AuctionPost>> auctionsByCategory = myPosts.stream().collect(Collectors.groupingBy(AuctionPost::getCategory));
        Map<String, Double> auctionsByCategoryCount = new HashMap<>();
        for (Category k : auctionsByCategory.keySet()) {
            List<AuctionPost> posts = auctionsByCategory.get(k);
            AtomicReference<Double> price= new AtomicReference<>(0.0);
            posts.forEach(x-> price.updateAndGet(v -> v + (x.getHighestBid().getOffer() - x.getMinPrice())));
            auctionsByCategoryCount.put(k.name(),price.get()/posts.size());

        }
        return auctionsByCategoryCount;
    }

    private User getUser(User user) {
        AuctionHouse house = auctionHouseService.getAuctionHouseByEmail(user.getEmail());
        if (house != null) {
            return house;
        } else {
            RegularUser regularUser = regularUserService.getUserByEmail(user.getEmail());
            return regularUser;
        }

    }
}
