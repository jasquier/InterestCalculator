package team.squad.builders;

import team.squad.accounts.Account;
import team.squad.accounts.PastTransaction;
import team.squad.accounts.RecurringTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John A. Squier
 * add your name when you work in this file
 */
public class AccountBuilder {

    private final Integer ID;
    private static Integer nextID = 1;
    private Long balance; // in pennies
    private Long ledgerBalance;
    private final String accountType; // does this even matter?
    private Double interestRate; // A.P.Y.
    private Long overDraftPenalty;
    private Long requiredMinimumBalance;
    private Boolean isMinimumBalanceRequired;
    private List<RecurringTransaction> recurringTransactions;
    private List<PastTransaction> accountHistory;


    public AccountBuilder(String accountType) {
        this.accountType = accountType;
        this.ID = nextID++;
        this.balance = 0L;
        this.interestRate = 0.0;
        this.overDraftPenalty = 0L;
        this.requiredMinimumBalance = 0L;
        this.isMinimumBalanceRequired = false;
        this.recurringTransactions = new ArrayList<>();
        this.accountHistory = new ArrayList<>();
    }

    public AccountBuilder balance(Long balance) {
        this.balance = balance;
        return this;
    }

    public AccountBuilder interestRate(Double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public AccountBuilder overDraftPenalty(Long overDraftPenalty) {
        this.overDraftPenalty = overDraftPenalty;
        return this;
    }

    public AccountBuilder requiredMinimumBalance(Long requiredMinimumBalance) {
        if ( requiredMinimumBalance == 0L ) {
            this.requiredMinimumBalance = 0L;
            this.isMinimumBalanceRequired = false;
        }
        else {
            this.requiredMinimumBalance = requiredMinimumBalance;
            this.isMinimumBalanceRequired = true;
        }
        return this;
    }

    public AccountBuilder recurringTransactions(List<RecurringTransaction> recurringTransactions) {
        this.recurringTransactions = recurringTransactions;
        return this;
    }

    public AccountBuilder accountHistory(List<PastTransaction> accountHistory) {
        this.accountHistory = accountHistory;
        return this;
    }

//    public Account build() {
//        return new Account(this);
//    }
}
