package at.ac.ase.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "auctionProperty")})
public class ContactForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name="auctionProperty")
    private AuctionPost auctionPost;

    @NotNull
    @OneToOne
    private User user;

    @NotNull
    @Embedded
    private Address address;

    @NotNull
    @Column
    private String phoneNr;

    @NotNull
    @Pattern(regexp="^[a-zA-Z0-9_.+-üäöß]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,3}$",
            message="Please provide a valid email address")
    @Column
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuctionPost getAuctionPost() {
        return auctionPost;
    }

    public void setAuctionPost(AuctionPost auctionPost) {
        this.auctionPost = auctionPost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ContactForm{" +
                "id=" + id +
                ", auctionPost=" + auctionPost +
                ", user=" + user +
                ", address=" + address +
                ", phoneNr='" + phoneNr + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
