package at.ac.ase.service.users.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.service.users.AuctionHouseService;

import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuctionHouseServiceImpl implements AuctionHouseService {

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<AuctionHouse> getAuctionHouseById(Long id) {
        return auctionHouseRepository.findById(id);
    }

    @Override
    public AuctionHouse getAuctionHouseByEmail(String email) {
        return auctionHouseRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void changePassword(String email, String password) {
        String passwordHash = passwordEncoder.encode(password);
        auctionHouseRepository.changePassword(email, passwordHash);
    }
}
