package at.ac.ase.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot subscribe to the auction if you're an auction house or if you created the auction!")
public class WrongSubscriberException extends RuntimeException{
    public WrongSubscriberException(){}
}
