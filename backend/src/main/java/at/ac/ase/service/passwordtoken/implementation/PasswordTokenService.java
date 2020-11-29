package at.ac.ase.service.passwordtoken.implementation;

import at.ac.ase.entities.PasswordResetToken;
import at.ac.ase.postgres.token.PasswordTokenRepository;
import at.ac.ase.service.passwordtoken.IPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordTokenService implements IPasswordTokenService {

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Override
    public PasswordResetToken getPasswordResetTokenByToken(int token) {
        return passwordTokenRepository.findByToken(token);
    }
}
