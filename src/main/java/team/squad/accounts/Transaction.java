package team.squad.accounts;

import javax.persistence.*;

/**
 * @author John A. Squier
 */

public abstract class Transaction {

    private Long amount;

    //private Long accountNum;

    public Transaction() {

    }

    public Transaction(Long amount) {
        this.amount = amount;
    }

   /* public Long getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(Long accountNum) {
        this.accountNum = accountNum;
    }*/

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
