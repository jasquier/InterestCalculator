package team.squad;

import org.junit.Before;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class IntervalTest {

    private Interval hourly, daily, monthly, annually;

    @Before
    public void setup() {
        hourly = Interval.HOURLY;
        daily = Interval.DAILY;
        monthly = Interval.MONTHLY;
        annually = Interval.ANNUALLY;
    }
}
