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
        if(!isUnderRMB())
            interestAmount = (long) (account.getBalance()*account.getInterestRate()*(interval/365));
        else
            interestAmount = 0L;
    }

    public void calculateComplexInterest() {

        //A = P (1 + r/n) (nt)

        interestAmount = (long) Math.pow(account.getBalance()*((1 + (account.getInterestRate()/frequency))), frequency*interval) - account.getBalance();
    }

    protected boolean isUnderRMB(){
        return (account.getBalance() <= account.getRequiredMinimumBalance());
    }

    protected double setRMBinterest() {
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

    protected long setOverDraftPenalty(){
        return (isOverDrawn()) ? account.getOverDraftPenalty() : 0;
    }



}