package team.squad.accounts;

/**
 * @author John A. Squier
 */
public abstract class Transaction {

    private Long amount;

    public Transaction() {
        this.amount = 0L;
    }

    public Transaction(Long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
