package at.ac.ase.service.auth;

import at.ac.ase.entities.Address;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
public interface IRegisterService {

    public RegularUser registerUser(RegularUser user);
    public AuctionHouse registerHouse(AuctionHouse auctionHouse);
    public Address registerAddress(Address address);
}
