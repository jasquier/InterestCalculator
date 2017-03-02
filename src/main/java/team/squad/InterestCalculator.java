package team.squad;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 *
 * FROM THE FUNCTIONAL REQUIREMENTS PDF
 * "InterestCalculator class should have a calculateSimpleInterest method that computes SIMPLE interest over
 *      a given interval ?? from the objects state ?? (how long is the interval? what object's state? the account?)"
 *
 * "InterestCalculator should have a calculateComplexInterest method that compues COMPLEX interest over a given
 *      interval ?? with a given compounding period (daily, monthly, annually, intermediate)"
 */
public class InterestCalculator {

    private Account account;
    private Interval interval;
    private Interval frequency; // a.k.a compounding period
    private Long interestAmount;
    private InterestType interestType;
    private CalculationRule calculationRule;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public void setFrequency(Interval frequency) {
        this.frequency = frequency;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }

    public void setCalculationRule(CalculationRule calculationRule) {
        this.calculationRule = calculationRule;
    }

    public Long getInterestAmount() {
        interestAmount = 1337L;
        return interestAmount;
    }

    public long calculateSimpleInterest() {
        return -1;
    }

    public long calculateComplexInterest() {
        return -1;
    }
}