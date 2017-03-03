package team.squad.interest;

import team.squad.accounts.Account;

/**
 * @author John A. Squier
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
    private Integer interval;   // in days
    private Integer frequency; // a.k.a compounding period in days
    private Long interestAmount;
    private InterestType interestType;
    private CalculationRule calculationRule;
    private Integer numDaysForRule;



    public Long getInterestAmount() {
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

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }

    public void setCalculationRule(CalculationRule calculationRule) {
        this.calculationRule = calculationRule;
    }

    public void setNumDaysForRule(Integer numDaysForRule) {
        this.numDaysForRule = numDaysForRule;
    }

    public void calculateSimpleInterest() {
        if( canEarnInterest())
            interestAmount = (long) (account.getBalance()*setRMBInterest()*(interval/365));
        else
            interestAmount = 0L;
    }

    public void calculateComplexInterest() {
        if( canEarnInterest() )
        {
            //A = P (1 + r/n) (nt)
            long initialPrinciple = account.getBalance() - getOverDraftPenalty();
            double rate = setRMBInterest();
            double compoundedOverYears = frequency * (interval/365);
            interestAmount = (long) (initialPrinciple * (Math.pow(1+ rate, compoundedOverYears) - 1));
        }
        else
            interestAmount = 0L;
    }

    protected boolean isUnderRMB(){
        return (account.getBalance() <= account.getRequiredMinimumBalance());
    }

    protected double setRMBInterest() {
        return (account.getIsMinimumBalanceRequired() && isUnderRMB()) ? 0.00 : account.getInterestRate();
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
        return  account.getIsMinimumBalanceRequired() && account.getBalance() < 0;
    }

    protected long getOverDraftPenalty(){
        return (isOverDrawn()) ? account.getOverDraftPenalty() : 0;
    }

    protected double getOverDraftInterest(){
        return (isOverDrawn()) ? 0.00 : account.getInterestRate();
    }

    private double getInterestRate() {
        double rate;
        rate = getOverDraftInterest();
        rate = setRMBInterest();
        return rate;
    }

    private boolean canEarnInterest(){
        return !isUnderRMB() &&  isPositiveBalance();
    }

    private boolean isPositiveBalance() {
        return account.getBalance() > 0;
    }

    protected long getAverageAccountBalance(){
        return account.AverageBalance();
    }


}