package at.ac.ase.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "RegularUser")
public class RegularUser extends User {

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
        name = "regular_user_preferences",
        joinColumns = @JoinColumn(name = "user_id")
    )
    private Set<Category> preferences = new HashSet<>();

    @Column
    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;

    @Column
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;

    @Embedded
    private Address address;

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private Set<Bid> bids = new HashSet<>();

    public RegularUser(){}

    public Set<Category> getPreferences() {
        return preferences;
    }

    public void setPreferences(Set<Category> preferences) {
        this.preferences = preferences;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
