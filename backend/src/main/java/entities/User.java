package entities;

import org.springframework.context.annotation.Bean;

import java.util.List;

public abstract class User {

    private Long id;
    private String name;
    private String email;
    private String phoneNr;
    private String passwordHash;
    private Location location;

    public List<AuctionPost> getOwnAuctions() {
        return ownAuctions;
    }

    public void setOwnAuctions(List<AuctionPost> ownAuctions) {
        this.ownAuctions = ownAuctions;
    }

    private List<AuctionPost> ownAuctions;

    protected User() {
    }

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

    public Location getLocation() {
        return location;
    }

    @Bean
    public void setLocation(Location location) {
        this.location = location;
    }
}
