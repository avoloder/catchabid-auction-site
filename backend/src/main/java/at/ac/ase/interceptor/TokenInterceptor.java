package at.ac.ase.interceptor;

import at.ac.ase.util.exception.TokenUtil;
import at.ac.ase.util.exception.exceptionhandler.AuthorizationException;
import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public class TokenInterceptor implements HandlerInterceptor {

    public static final String AUTHORIZATION = "Authorization";

    private static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(AUTHORIZATION);
        try {
            boolean isTokenValid = TokenUtil.verifyToken(token);
            String userEmail = TokenUtil.getEmailFromToken(token);
            if (!isTokenValid){
                throw new AuthorizationException();
            }
        } catch (ParseException | JOSEException e){
            throw new AuthorizationException();
        }
        return true;
    }
}
