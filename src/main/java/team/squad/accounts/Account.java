package team.squad.accounts;

import team.squad.builders.AccountBuilder;

import java.util.Arrays;
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

    private String ID;
    private Long balance; // in pennies
    private Long ledgerBalance;
    private String accountType; // does this even matter?
    private Double interestRate; // A.P.Y.
    private Long overDraftPenalty;
    private Long requiredMinimumBalance;
    private Boolean isMinimumBalanceRequired;
    private List<RecurringTransaction> recurringTransactions;
    private List<PastTransaction> accountHistory;

    public Account(AccountBuilder builder) {


    }

    public Account() {

    }
//
//
//    public static Account getAccountInfoByID(String ID) {
//        // this will hit the database at some point
//        // this is most definitely wrong
//        return new Account();
//    }

    public String getID() { return "27"; }

    public Long getBalance() {
        return balance;
    }

    public Long getLedgerBalance() {
        return ledgerBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Long getOverDraftPenalty() {
        return overDraftPenalty;
    }

    public Long getRequiredMinimumBalance() {
        return requiredMinimumBalance;
    }

    public Boolean getIsMinimumBalanceRequired() {
        return isMinimumBalanceRequired;
    }

    public List<RecurringTransaction> getRecurringTransactions() {
        return recurringTransactions;
    }

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
        return;
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

    /* This method loops over the list of recurring transactions and computes
    the average balance of the account at each period.
    The actual formula used is just ( t[start] + t[end] ) / 2
    This is done for each recurring transaction in the list of recurring transactions . */
    public long getAverageBalance() {
        long[] netRecurringTransactions = new long[getRecurringTransactions().size()];
        long localBalance = getBalance();
        long avgBalance = 0;

        for(int j=0; j<recurringTransactions.size(); j++){
            for(int i=0; i<recurringTransactions.get(j).getFrequency(); i++){
                netRecurringTransactions[j] += recurringTransactions.get(j).getAmount();
            }
            avgBalance = (localBalance + localBalance + netRecurringTransactions[j] ) / 2;
            localBalance = avgBalance;
        }
        return avgBalance;
    }
}