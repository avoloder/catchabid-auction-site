package at.ac.ase.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String info;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "notification_user",
        joinColumns = { @JoinColumn(name = "notification_id") },
        inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<RegularUser> receivers = new HashSet<>();

    @OneToOne
    private AuctionPost auctionPost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<RegularUser> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<RegularUser> receivers) {
        this.receivers = receivers;
    }

    public AuctionPost getAuctionPost() {
        return auctionPost;
    }

    public void setAuctionPost(AuctionPost auctionPost) {
        this.auctionPost = auctionPost;
    }
}
