package at.ac.ase.service.passwordtoken.implementation;

import at.ac.ase.entities.AuctionHouse;
import at.ac.ase.entities.PasswordResetToken;
import at.ac.ase.entities.RegularUser;
import at.ac.ase.postgres.token.PasswordTokenRepository;
import at.ac.ase.postgres.users.AuctionHouseRepository;
import at.ac.ase.postgres.users.UserRepository;
import at.ac.ase.service.passwordtoken.IPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PasswordTokenService implements IPasswordTokenService {

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuctionHouseRepository auctionHouseRepository;

    @Override
    @Transactional
    public PasswordResetToken getPasswordResetTokenByToken(String email, int token) {
        RegularUser user = userRepository.findByEmail(email);
        AuctionHouse auctionHouse = auctionHouseRepository.findByEmail(email);
        if(user == null && auctionHouse == null){
            System.out.println("nema");
            throw new UsernameNotFoundException("not found");
        }
        PasswordResetToken t =  passwordTokenRepository.findByToken(user, auctionHouse, token);
        if(t == null){
            System.out.println("nema ga");
        }
        System.out.println(t);
        return t;
    }
}
