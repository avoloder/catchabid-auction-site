package at.ac.ase.service.passwordtoken;

import at.ac.ase.entities.PasswordResetToken;

public interface IPasswordTokenService {

    PasswordResetToken getPasswordResetTokenByToken(int token);
}
