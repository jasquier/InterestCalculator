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
    private InterestType interestType;

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setInterval(Interval interval) {
        this.interval = interval;
    }

    public void setInterestType(InterestType interestType) {
        this.interestType = interestType;
    }
}