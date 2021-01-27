package at.ac.ase.util.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Auction house is not allowed to bid on auctions!")
public class AuctionHouseNotAllowedException extends RuntimeException{
    public AuctionHouseNotAllowedException() {
    }
}
