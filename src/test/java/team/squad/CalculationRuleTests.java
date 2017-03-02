package team.squad;

import org.junit.Before;

/**
 * @author John A. Squier
 */
public class CalculationRuleTests {

    private CalculationRule average, maximum, minimum, timeOfCredit, exInterestDate,
            thresholdMaximum, thresholdMinimum;

    @Before
    public void setup() {
        average = CalculationRule.AVERAGE;
        maximum = CalculationRule.MAXIMUM;
        minimum = CalculationRule.MINIMUM;
        timeOfCredit = CalculationRule.TIME_OF_CREDIT;
        exInterestDate = CalculationRule.EX_INTEREST_DATE;
        thresholdMaximum = CalculationRule.THRESHOLD_MAXIMUM;
        thresholdMinimum =  CalculationRule.THRESHOLD_MINIMUM;
    }
}
