package entities;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class Bid {
    private Long id;
    private Long auctionId;
    private Long userId;
    private Double offer;
    private Timestamp timestamp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(Long auctionId) {
        this.auctionId = auctionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getOffer() {
        return offer;
    }

    public void setOffer(Double offer) {
        this.offer = offer;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    //@Component
    //@Entity @IdClass(BidId.class)
    //@RedisHash("BestBid")
    //@Table(name = "bid")
    //public class Bid {
    //    @Id private Long auctionId;
    //    @Id private Long userId;
    //    private Double offer;
    //    private Timestamp timestamp;
    //
}
