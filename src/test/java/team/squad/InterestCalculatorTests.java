package team.squad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class InterestCalculatorTests {

    InterestCalculator interestCalculator;
    Account account;

    @Before
    public void setup() {

        account = new Account();
        account.setBalance(100000L); //$1000.00
        account.setInterestRate(0.10); // 10% APY
        account.setAccountType("savings");
        account.setOverDraftPenalty(0L); // when under $0.00 no interest

        interestCalculator = new InterestCalculator();
        interestCalculator.setInterestType(InterestType.SIMPLE);
        interestCalculator.setCalculationRule(CalculationRule.NONE);
        interestCalculator.setInterval(365);
        interestCalculator.setAccount(account);

    }

    @Test
    public void calcSimpleInterestNormalNoRMB(){
        long expected = 10000L;
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        interestCalculator.calculateSimpleInterest();
        long actual = interestCalculator.getInterestAmount();


        Assert.assertEquals("I expect the interest to be $100.00",
                expected, actual);
    }

    @Test
    public void calcSimpleInterestNormalRMB(){
        long expected = 10000L;
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(0L);

        interestCalculator.calculateSimpleInterest();
        long actual = interestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNormalBelowRMB(){
        long expected = 0L;
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(500000L);

        interestCalculator.calculateSimpleInterest();
        long actual = interestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }
}
