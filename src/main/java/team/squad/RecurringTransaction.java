package team.squad;

/**
 * @author John A. Squier
 * add your name when you work on this file
 *
 * TODO figure out if getters are needed or not
 */
public class RecurringTransaction extends Transaction {

    private Integer frequency;

    public RecurringTransaction() {
        this.frequency = 12;
    }

    public RecurringTransaction(Long amount, Integer frequency) {
        super(amount);
        this.frequency = frequency;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}