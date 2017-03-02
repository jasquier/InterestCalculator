package team.squad;

import java.util.List;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 *
 * TODO figure out if the instance fields need to be Objects or if they can be primitives i.e. long vs Long.
 * TODO is the transaction history what we would make persistent?
 * TODO what is in the transaction history? the recurring transacitons and interest additions?
 *
 * FROM THE FUNCITONAL REQUIREMENTS PDF
 * "Account object with balance ✓ & interest rate ✓ properties, TRANSACTION HISTORY, overdraft ✓
 *      & minimum balance info ✓ & a list of recurring transactions ✓"
 */
public class Account {

    private Long balance; // in pennies
    private String accountType;
    private Double interestRate;
    private Long overDraftPenalty;
    private Long requiredMinimumBalance;
    private Boolean isMinimumBalanceRequired;
    private List<RecurringTransaction> recurringTransactions;

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public void setOverDraftPenalty(Long overDraftPenalty) {
        this.overDraftPenalty = overDraftPenalty;
    }

    public void setRequiredMinimumBalance(Long requiredMinimumBalance) {
        this.requiredMinimumBalance = requiredMinimumBalance;
    }

    public void setMinimumBalanceRequired(Boolean minimumBalanceRequired) {
        isMinimumBalanceRequired = minimumBalanceRequired;
    }

    public void setRecurringTransactions(List<RecurringTransaction> recurringTransactions) {
        this.recurringTransactions = recurringTransactions;
    }
}