package at.ac.ase.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    @Column
    private Boolean active = true;

    @Column
    @NotNull
    @Pattern(regexp="^[a-zA-Z0-9_.+-üäöß]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,3}$",
        message="Please provide a valid email address")
    private String email;

    @Column
    private String phoneNr;

    @Column
    private String passwordHash;

    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "user",
        orphanRemoval = true)
    private Set<Rating> ratings = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "auction_subscriptions",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "auction_post_id") })
    private Set<AuctionPost> auctionSubscriptions = new HashSet<>();

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "user",
        orphanRemoval = true)
    private Set<Bid> bids = new HashSet<>();

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "creator",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private Set<AuctionPost> ownedAuctions = new HashSet<>();

    public User(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Rating> ratings) {
        this.ratings = ratings;
    }

    public Set<AuctionPost> getAuctionSubscriptions() {
        return auctionSubscriptions;
    }

    public void setAuctionSubscriptions(Set<AuctionPost> auctionSubscriptions) {
        this.auctionSubscriptions = auctionSubscriptions;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Set<AuctionPost> getOwnedAuctions() {
        return ownedAuctions;
    }

    public void setOwnedAuctions(Set<AuctionPost> ownedAuctions) {
        this.ownedAuctions = ownedAuctions;
    }
}
