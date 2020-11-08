package entities;

import org.springframework.stereotype.Component;

@Component
public class AuctionHouse extends User {
    private String serialNr;

    public String getSerialNr() {
        return serialNr;
    }

    public void setSerialNr(String serialNr) {
        this.serialNr = serialNr;
    }
}
