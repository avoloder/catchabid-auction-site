package at.ac.ase.service.auth.implementation;

import at.ac.ase.entities.Address;
import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
import at.ac.ase.postgres.address.AddressRepository;
import at.ac.ase.postgres.auction.AuctionRepository;
import at.ac.ase.postgres.users.UserRepository;
import at.ac.ase.service.auth.IRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuctionRepository auctionRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public RegularUser registerUser(RegularUser userToRegister) {
        userToRegister.setPasswordHash(passwordEncoder.encode(userToRegister.getPasswordHash()));
        return (RegularUser) userRepository.save(userToRegister);
    }

    @Override
    public AuctionHouse registerHouse(AuctionHouse ahouse) {
        ahouse.setPasswordHash(passwordEncoder.encode(ahouse.getPasswordHash()));
        return (AuctionHouse) auctionRepository.save(ahouse);
    }

    @Override
    public Address registerAddress(Address address) {
        return (Address) addressRepository.save(address);
    }


}
