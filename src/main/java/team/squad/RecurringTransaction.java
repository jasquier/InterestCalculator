package team.squad;

/**
 * @author John A. Squier
 * add your name when you work on this
 *
 * TODO figure out if getters are needed or not
 */
public class RecurringTransaction {

    private Long amount;
    private Integer frequency;

    public Long getAmount() {
        return amount;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}