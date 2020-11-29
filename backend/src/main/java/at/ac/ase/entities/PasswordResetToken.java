package at.ac.ase.entities;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table
public class PasswordResetToken {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int token;

    @OneToOne(targetEntity = RegularUser.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private RegularUser user;

    @OneToOne(targetEntity = AuctionHouse.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "auction_house_id")
    private AuctionHouse auctionHouse;

    @Column
    private Date expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(int token, RegularUser user, AuctionHouse auctionHouse) {
        this.token = token;
        this.user = user;
        this.auctionHouse = auctionHouse;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public RegularUser getUser() {
        return user;
    }

    public void setUser(RegularUser user) {
        this.user = user;
    }

    public AuctionHouse getAuctionHouse() {
        return auctionHouse;
    }

    public void setAuctionHouse(AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    private Date calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
