package at.ac.ase.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class AuctionPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "creator")
    private AuctionHouse creator;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    @NotNull
    private LocalDateTime startTime;

    @Column
    @NotNull
    private LocalDateTime endTime;

    @Column
    @NotNull
    private Double minPrice;

    @Column
    private String description;

    @Column
    @Lob
    private byte[] image;

    @NotNull
    @Column
    private String name;

    @OneToOne
    @JoinColumn(name = "bid_id", referencedColumnName = "id")
    private Bid highestBid;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "auction_subscriptions",
        joinColumns = { @JoinColumn(name = "auction_post_id") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<RegularUser> subscriptions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuctionHouse getCreator() {
        return creator;
    }

    public void setCreator(AuctionHouse creator) {
        this.creator = creator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Bid getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(Bid highestBid) {
        this.highestBid = highestBid;
    }

    public Set<RegularUser> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<RegularUser> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
