package at.ac.ase.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table
@JsonInclude(Include.NON_NULL)
public class AuctionHouse extends User {

    @Column
    @NotNull
    private String tid;

    @Column
    @NotNull
    @Size(min = 1, max = 50)
    private String name;

    public AuctionHouse(){}

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

}
