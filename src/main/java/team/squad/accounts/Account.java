package team.squad.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 *
 * TODO figure out if the instance fields need to be Objects or if they can be primitives i.e. long vs Long.
 * TODO is the transaction history what we would make persistent?
 * TODO what is in the transaction history? the recurring transacitons and interest additions?
 * TODO figure out if the getters need removed, are they for testing only or do we need them?
 *
 * FROM THE FUNCITONAL REQUIREMENTS PDF
 * "Account object with balance ✓ & interest rate ✓ properties, TRANSACTION HISTORY (is this ledger balance?),
 *      overdraft ✓ & minimum balance info ✓ & a list of recurring transactions ✓"
 */
public class Account {

    private Integer ID;
    private Long balance; // in pennies
    private Long ledgerBalance;
    private String accountType; // does this even matter?
    private Double interestRate; // A.P.Y.
    private Long overDraftPenalty;
    private Long requiredMinimumBalance;
    private Boolean isMinimumBalanceRequired;
    private List<RecurringTransaction> recurringTransactions;
    private List<PastTransaction> accountHistory;

    public Account() { }

    public Integer getID() { return 27; }

    @JsonIgnore
    public Long getBalance() {
        return balance;
    }

    @JsonIgnore
    public Long getLedgerBalance() {
        return ledgerBalance;
    }

    @JsonIgnore
    public String getAccountType() {
        return accountType;
    }

    @JsonIgnore
    public Double getInterestRate() {
        return interestRate;
    }

    @JsonIgnore
    public Long getOverDraftPenalty() {
        return overDraftPenalty;
    }

    @JsonIgnore
    public Long getRequiredMinimumBalance() {
        return requiredMinimumBalance;
    }

    @JsonIgnore
    public Boolean getIsMinimumBalanceRequired() {
        return isMinimumBalanceRequired;
    }

    @JsonIgnore
    public List<RecurringTransaction> getRecurringTransactions() {
        return recurringTransactions;
    }

    @JsonIgnore
    public List<PastTransaction> getAccountHistory() {
        return accountHistory;
    }

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

    public void setIsMinimumBalanceRequired(Boolean isMinimumBalanceRequired) {
        this.isMinimumBalanceRequired = isMinimumBalanceRequired;
    }

    public void setRecurringTransactions(List<RecurringTransaction> recurringTransactions) {
        this.recurringTransactions = recurringTransactions;
    }

    public void setAccountHistory(List<PastTransaction> accountHistory) {
        this.accountHistory = accountHistory;
    }

    public void calculateLedgerBalance(Integer interval) {
        ledgerBalance = balance + getAdjustedBalance(interval);
    }

    private Long getAdjustedBalance(Integer interval) {
        Long adjustedAmount = 0L;

        for ( RecurringTransaction r : recurringTransactions ) {
            if ( r.getFrequency().equals(365) ) {
                adjustedAmount += r.getAmount() * interval;
            }
            else if ( r.getFrequency().equals(12) ) {
                adjustedAmount += r.getAmount() * (interval / 30);
            }
            else if ( r.getFrequency().equals(1) ) {
                adjustedAmount += r.getAmount() * (interval / 365);
            }
        }
        return adjustedAmount;
    }

    public static Account createBlankAccount() {
        Account toReturn = new Account();
        toReturn.setBalance(0L);
        toReturn.setAccountType("savings");
        toReturn.setInterestRate(0.0);
        toReturn.setOverDraftPenalty(0L);
        toReturn.setRequiredMinimumBalance(0L);
        toReturn.setIsMinimumBalanceRequired(false);
        toReturn.setRecurringTransactions(new ArrayList<>());
        toReturn.setAccountHistory(new ArrayList<>());

        return toReturn;
    }
}