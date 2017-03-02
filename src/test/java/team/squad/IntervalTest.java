package team.squad;

import org.junit.Before;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class IntervalTest {

    private Interval daily, monthly, annually;

    @Before
    public void setup() {
        daily = Interval.DAILY;
        monthly = Interval.MONTHLY;
        annually = Interval.ANNUALLY;
    }
}
