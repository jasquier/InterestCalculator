package team.squad.interest;

import team.squad.accounts.Account;
import team.squad.accounts.AccountStore;
import team.squad.accounts.PastTransaction;

import java.util.Collections;
import java.util.*;

/**
 * @author John A. Squier
 * @author Milton Marwa
 * @author Randall Crane
 * add your name when you work on this file.
 *
 * TODO remove getters except interestAmount, others are for test purposes, I think
 *
 * FROM THE FUNCTIONAL REQUIREMENTS PDF
 * "InterestCalculator class should have a calculateSimpleInterest method that computes SIMPLE interest over
 *      a given interval ?? from the objects state ?? (how long is the interval? what object's state? the account?)"
 *
 * "InterestCalculator should have a calculateComplexInterest method that compues COMPLEX interest over a given
 *      interval ?? with a given compounding period (daily, monthly, annually, intermediate)"
 */
public class InterestCalculator {

    private Account account; // get acct number perhaps and hit the backend
    private Integer accountID;
    private Integer interval;   // in days
    private Integer frequency; // a.k.a num times per year we compound
    private Long interestAmount;
    private InterestType interestType;
    private CalculationRule calculationRule;
    private Integer numDaysForRule;

    public InterestCalculator() { }

    public InterestCalculator(Account ac, int interval, InterestType interestType, CalculationRule calcRule) {
        this.account = ac;
        this.interval = interval;
        //this.frequency = freq;
        this.interestType = interestType;
        this.calculationRule = calcRule;
    }

    public Long getInterestAmount() {
        System.out.println("ID = " + account.getID());

        if ( interestType.equals(InterestType.SIMPLE) ) {
            calculateSimpleInterest();
        }
        else {
            calculateComplexInterest();
        }
        return interestAmount;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setAccountID(Integer accountID) {
        System.out.println("setting accountID = " + accountID);
        this.accountID = accountID;
        System.out.println("setting account by ID = " + this.accountID);
        this.account = AccountStore.getAccountByID(accountID);
        System.out.println("account balance = " + account.getBalance());
    }

    public void setInterval(Integer interval) {
        System.out.println("setting interval = " + interval);
        this.interval = interval;
    }

    public void setFrequency(Integer frequency) {
        System.out.println("setting frequency = " + frequency);
        this.frequency = frequency;
    }

    public void setInterestType(InterestType interestType) {
        System.out.println("setting interest type = " + interestType);
        this.interestType = interestType;
    }

    public void setCalculationRule(CalculationRule calculationRule) {
        System.out.println("setting rule = " + calculationRule);
        this.calculationRule = calculationRule;
    }

    public void setNumDaysForRule(Integer numDaysForRule) {
        System.out.println("setting numDaysForRule = " + numDaysForRule);
        this.numDaysForRule = numDaysForRule;
    }

    public void calculateSimpleInterest() {
        if( canEarnInterest())
            interestAmount = (long) (getAccountBalance()*account.getInterestRate()*(interval/365));
        else
            interestAmount = 0L;
    }

    public void calculateComplexInterest() {
        //A = P (1 + r/n) (nt)
        long initialPrinciple = account.getBalance() - getOverDraftPenalty();
        double rate = getInterestRate()/frequency;
        double compoundedOverYears = frequency * (interval/360);
        interestAmount = (long) (initialPrinciple * (Math.pow(1+ rate, compoundedOverYears) - 1));
    }

    protected boolean isUnderRMB(){
        return getAccountBalance() < account.getRequiredMinimumBalance();
    }

    protected double getRMBInterest() {
        return (isUnderRMB()) ? 0.00 : account.getInterestRate();
    }

    protected boolean isThereAccountHistory(){
        return this.account.getAccountHistory() != null;
    }

    protected boolean isThereRecurringTransactions(){
        return this.account.getRecurringTransactions() != null;
    }

    public boolean calculateBalancesInOrder(){
        return isThereAccountHistory() || isThereRecurringTransactions();
    }

    protected boolean isOverDrawn(){
        return account.getBalance() < 0;
    }

    protected long getOverDraftPenalty(){
        return (isOverDrawn()) ? account.getOverDraftPenalty() : 0;
    }

    private double getInterestRate() {
        double rate;
        if (canEarnInterest())
            return account.getInterestRate();
        else if (isOverDrawn())
            return 0.00;
        else
            return getRMBInterest();

    }

    private boolean canEarnInterest(){
            return !isUnderRMB() &&  isPositiveBalance();
    }

    private boolean isPositiveBalance() {
        return getAccountBalance() > 0;
    }

    protected long getAccountBalance(){
        long balance = 0L;
        switch (calculationRule){
            case AVERAGE:
                balance = account.getAverageBalance();
                break;

            case EX_INTEREST_DATE:
                balance = 333333L;
                break;

            case MAXIMUM:
                balance = 333333L;
                break;

            case MINIMUM:
                balance = 333333L;
                break;

            case NONE:
                balance = account.getBalance();
                break;

            case THRESHOLD_MAXIMUM:
                balance = account.getMinimumBalance();
                break;

            case THRESHOLD_MINIMUM:
                balance = 333333L;
                break;

            case TIME_OF_CREDIT:
                balance = 333333L;
                break;

            default:
                balance = account.getBalance();
        }
        return  balance;
    }

    public long balanceMinimum(){
        List<PastTransaction> accountHistory = account.getAccountHistory();
        ArrayList<Long> balanceHistory = new ArrayList<>();
        long balance = account.getBalance();
        Collections.reverse(accountHistory);

        for (PastTransaction item: accountHistory) {
            balance -= item.getAmount();
            balanceHistory.add(balance);
        }
        Collections.sort(balanceHistory);
        return balanceHistory.get(0);
    }
}
