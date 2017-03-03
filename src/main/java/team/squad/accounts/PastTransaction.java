package team.squad.accounts;

import java.util.Date;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class PastTransaction extends Transaction {

    private Date dateOccurredOn;

    public PastTransaction() {
        dateOccurredOn = new Date(2016, 1, 1);
    }

    public PastTransaction(Long amount, Date dateOccurredOn) {
        super(amount);
        this.dateOccurredOn = dateOccurredOn;
    }

    public Date getDateOccurredOn() {
        return dateOccurredOn;
    }

    public void setDateOccurredOn(Date dateOccurredOn) {
        this.dateOccurredOn = dateOccurredOn;
    }
}
