package team.squad.accounts;

import javax.persistence.*;
import java.util.Date;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
@Entity
@Table(name = "account_history")
public class PastTransaction {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;
    private Long amount;


    private Date dateOccurredOn;

    public PastTransaction() {
        dateOccurredOn = new Date(2016, 1, 1);
    }

    public PastTransaction(Long amount, Date dateOccurredOn) {
        this.amount = amount;
        this.dateOccurredOn = dateOccurredOn;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }



    public Date getDateOccurredOn() {
        return dateOccurredOn;
    }

    public void setDateOccurredOn(Date dateOccurredOn) {
        this.dateOccurredOn = dateOccurredOn;
    }
}
