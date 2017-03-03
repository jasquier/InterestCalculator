package team.squad;

/**
 * @author John A. Squier
 * add your name when you work on this file
 *
 * TODO figure out if getters are needed or not
 */
public class RecurringTransaction extends Transaction {

    private Interval frequency;

    public RecurringTransaction(Long amount, Interval frequency) {
        super(amount);
        this.frequency = frequency;
    }

    public Interval getFrequency() {
        return frequency;
    }

    public void setFrequency(Interval frequency) {
        this.frequency = frequency;
    }
}