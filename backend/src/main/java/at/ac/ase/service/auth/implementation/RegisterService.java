package at.ac.ase.service.auth.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.entities.User;
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
    PasswordEncoder passwordEncoder;

    @Override
    public RegularUser registerUser(RegularUser userToRegister) {
        RegularUser user = new RegularUser();
        user.setName(userToRegister.getName());
        user.setFirstName(userToRegister.getFirstName());
        user.setLastName(userToRegister.getLastName());
        user.setEmail(userToRegister.getEmail());
        user.setAddress(userToRegister.getAddress());
        user.setPhoneNr(userToRegister.getPhoneNr());
        user.setPasswordHash(passwordEncoder.encode(userToRegister.getPasswordHash()));
        return (RegularUser) userRepository.save(user);
    }

    @Override
    public AuctionHouse registerHouse(AuctionHouse ahouse) {
        AuctionHouse auctionHouse = new AuctionHouse();
        auctionHouse.setTid(ahouse.getTid());
        auctionHouse.setName(ahouse.getName());
        auctionHouse.setPhoneNr(ahouse.getPhoneNr());
        auctionHouse.setAddress(ahouse.getAddress());
        auctionHouse.setPasswordHash(passwordEncoder.encode(ahouse.getPasswordHash()));
        auctionHouse.setEmail(ahouse.getEmail());
        return (AuctionHouse) auctionRepository.save(auctionHouse);
    }



}
