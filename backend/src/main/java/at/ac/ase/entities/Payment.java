package at.ac.ase.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;

import javax.persistence.Table;

@Table
@Entity
public class Payment {

    private String paymentIntentId;

    private LocalDateTime paymentDate;

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public void setPaymentIntentId(String paymentIntentId) {
        this.paymentIntentId = paymentIntentId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
