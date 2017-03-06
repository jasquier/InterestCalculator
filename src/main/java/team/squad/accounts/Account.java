package team.squad.accounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John A. Squier
 * @author Milton Marwa
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


    private Integer accountNumber;
    private static Integer nextID = 1;

    private Long balance; // in pennies
    private Long ledgerBalance;
    private String accountType; // does this even matter?
    private Double interestRate; // A.P.Y.
    private Long overDraftPenalty;
    private Long requiredMinimumBalance;
    private Boolean isMinimumBalanceRequired;
    private List<RecurringTransaction> recurringTransactions;
    private List<PastTransaction> accountHistory;

    public Account() {
        this.accountNumber = nextID++;
    }

    // created by milton marwa 03/04/2017 to ease creation of accounts for tests
    public Account(long balance, double interestRate, long RMB, RecurringTransaction recur){
        this.balance=balance;
        this.interestRate=interestRate;
        this.requiredMinimumBalance = RMB;
        this.recurringTransactions = new ArrayList<RecurringTransaction>();
        this.recurringTransactions.add(recur);
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    @JsonIgnore
    public Long getBalance() {
        System.out.println("in get balance");
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
        System.out.println("in balance setter and balance = " +this.balance);
    }

    @JsonIgnore
    public Long getLedgerBalance() {
        return ledgerBalance;
    }

    @JsonIgnore
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @JsonIgnore
    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    @JsonIgnore
    public Long getOverDraftPenalty() {
        return overDraftPenalty;
    }

    public void setOverDraftPenalty(Long overDraftPenalty) {
        this.overDraftPenalty = overDraftPenalty;
    }

    @JsonIgnore
    public Long getRequiredMinimumBalance() {
        return requiredMinimumBalance;
    }

    public void setRequiredMinimumBalance(Long requiredMinimumBalance) {
        this.requiredMinimumBalance = requiredMinimumBalance;
        this.setIsMinimumBalanceRequired();
    }

    @JsonIgnore
    public Boolean getIsMinimumBalanceRequired() {
        return isMinimumBalanceRequired;
    }

    private void setIsMinimumBalanceRequired() {
        this.isMinimumBalanceRequired = (requiredMinimumBalance > 0L);
    }

    @JsonIgnore
    public List<RecurringTransaction> getRecurringTransactions() {
        return recurringTransactions;
    }

    public void setRecurringTransactions(List<RecurringTransaction> recurringTransactions) {
        this.recurringTransactions = recurringTransactions;
    }

    @JsonIgnore
    public List<PastTransaction> getAccountHistory() {
        return accountHistory;
    }

    public void setAccountHistory(List<PastTransaction> accountHistory) {
        this.accountHistory = accountHistory;
    }

    public void calculateLedgerBalance(Integer interval) {
        ledgerBalance = balance + getAdjustedBalance(interval);
    }

    @JsonIgnore
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
    @JsonIgnore
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
