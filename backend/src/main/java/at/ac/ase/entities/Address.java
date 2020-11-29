package at.ac.ase.entities;

import javax.persistence.*;

@Embeddable
public class Address {

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String street;

    @Column
    private Integer houseNr;

    public Address() {
    }

    public Address(String country, String city, String street, Integer houseNr) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.houseNr = houseNr;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getHouseNr() {
        return houseNr;
    }

    public void setHouseNr(Integer houseNr) {
        this.houseNr = houseNr;
    }
}
