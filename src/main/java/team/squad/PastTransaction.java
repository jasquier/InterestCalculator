package team.squad;

import java.util.Date;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class PastTransaction extends Transaction {

    private Date dateOccurredOn;

    public Date getDateOccurredOn() {
        return dateOccurredOn;
    }

    public void setDateOccurredOn(Date dateOccurredOn) {
        this.dateOccurredOn = dateOccurredOn;
    }

    public PastTransaction(Long amount, Date dateOccurredOn) {
        super(amount);
        this.dateOccurredOn = dateOccurredOn;
    }
}
