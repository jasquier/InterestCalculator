package team.squad;

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

    public Account getAccount() {
        return account;
    }

    public Integer getInterval() {
        return interval;
    }

    public Long getInterestAmount() {
        interestAmount = 1337L;
        return interestAmount;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public InterestType getInterestType() {
        return interestType;
    }

    public CalculationRule getCalculationRule() {
        return calculationRule;
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

    public long calculateSimpleInterest() {
        return (long) (account.getBalance()*account.getInterestRate()*(interval/365));
    }

    public long calculateComplexInterest() {
        return -1;
    }

    protected boolean isUnderRMB(){
        return (account.getBalance() <= account.getRequiredMinimumBalance());
    }

    protected double setRMBinterest() {
        return (isUnderRMB()) ? 0.00 : account.getInterestRate();
    }

    public boolean isThereAccountHistory(){
        return this.account.getAccountHistory() != null;
    }

    public boolean isThereRecurringTransactions(){
        return this.account.getRecurringTransactions() != null;
    }

    public boolean calculateBalancesInOrder(){
        return isThereAccountHistory() || isThereRecurringTransactions();
    }

    protected boolean isOverDrawn(){
        return account.getBalance() < 0;
    }

    protected long setOverDraftPenalty(){
        return (isOverDrawn()) ? account.getOverDraftPenalty() : 0;
    }

}