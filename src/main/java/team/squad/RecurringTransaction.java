package team.squad;

/**
 * @author John A. Squier
 * add your name when you work on this file
 *
 * TODO figure out if getters are needed or not
 */
public class RecurringTransaction {

    private Long amount;
    private Interval frequency;

    public Long getAmount() {
        return amount;
    }

    public Interval getFrequency() {
        return frequency;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setFrequency(Interval frequency) {
        this.frequency = frequency;
    }
}