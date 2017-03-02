package team.squad;

/**
 * @author John A. Squier
 * add your name when you work on this file.
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