package team.squad;

/**
 * Created by johnsquier on 3/2/17.
 */
public class Transaction {

    String date;
    long amount;

    public Transaction(String date, long amount) {
        this.date = date;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
