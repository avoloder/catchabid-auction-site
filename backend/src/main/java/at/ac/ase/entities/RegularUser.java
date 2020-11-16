package at.ac.ase.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class RegularUser extends User {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "regular_user_preferences",
        joinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Category> preferences = new HashSet<>();

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Bid> bids = new HashSet<>();

    public Set<Category> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<Category> preferences) {
        this.preferences = preferences;
    }

    @Override
    public Set<Bid> getBids() {
        return bids;
    }

    @Override
    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }
}
