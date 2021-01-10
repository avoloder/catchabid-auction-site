package at.ac.ase.util.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Auction is cancelled!")
public class AuctionCancelledException extends RuntimeException{
    public AuctionCancelledException(){};
}
