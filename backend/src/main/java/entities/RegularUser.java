package entities;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RegularUser extends User {
    private List<Category> preferences;

    private List<AuctionPost> subscribed;

    public List<Category> getPreferences() {
        return preferences;
    }

    public void setPreferences(List<Category> preferences) {
        this.preferences = preferences;
    }

    public List<AuctionPost> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<AuctionPost> subscribed) {
        this.subscribed = subscribed;
    }
}
