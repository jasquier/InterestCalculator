package team.squad;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class InterestCalculatorTests {

    InterestCalculator simpleInterestCalculator, complexInterestCalculator;
    Account account;

    @Before
    public void setup() {
        account = new Account();
        account.setAccountType("savings");
        account.setInterestRate(0.10); // 10% APY
        account.setOverDraftPenalty(0L); // when under $0.00 no interest
        account.setRecurringTransactions(null);
        account.setAccountHistory(null);

        simpleInterestCalculator = new InterestCalculator();
        simpleInterestCalculator.setInterestType(InterestType.SIMPLE);
        simpleInterestCalculator.setCalculationRule(CalculationRule.NONE);
        simpleInterestCalculator.setInterval(365);
        simpleInterestCalculator.setAccount(account);

        complexInterestCalculator = new InterestCalculator();
        complexInterestCalculator.setAccount(account);
        complexInterestCalculator.setInterval(365);
        complexInterestCalculator.setFrequency(30); // monthly
        complexInterestCalculator.setInterestType(InterestType.COMPLEX);
        complexInterestCalculator.setCalculationRule(CalculationRule.NONE);
        complexInterestCalculator.setNumDaysForRule(0);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndNoRMB(){
        long expected = 10000L;
        account.setBalance(100000L); //$1000.00
        account.setRequiredMinimumBalance(0L);
        account.setIsMinimumBalanceRequired(false);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndAboveRMB(){
        long expected = 10000L;
        account.setBalance(100000L); //$1000.00
        account.setRequiredMinimumBalance(0L);
        account.setIsMinimumBalanceRequired(true);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestNonZeroBalanceAndBelowRMB(){
        long expected = 0L;
        account.setBalance(100000L); //$1000.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(500000L);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndNoRMB() {
        long expected = 0L;
        account.setBalance(0L); // $0.00
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestWithZeroBalanceAndBelowRMB() {
        long expected = 0L;
        account.setBalance(0L); // $0.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(50000L); // $500.00

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcSimpleInterestWithNegativeBalanceAndNoRMB() {
        long expected = 0L;
        account.setBalance(-100L); // $-1.00
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected ,actual);
    }

    @Test
    public void calcSimpleInterestWithNegativeBalanceAndBelowRMB() {
        long expected = 0L;
        account.setBalance(-100L); // $-1.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(50000L); // $500.00

        long actual = simpleInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected ,actual);
    }

    @Test
    public void calcComplexInterestWithNonZeroBalanceAndNoRMB() {
        long expected = -2L; // TODO calculate this value
        account.setBalance(500000L); // $5000.00
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestWithNonZeroBalanceAndAboveRMB() {
        long expected = -2L;
        account.setBalance(500000L); // $5000.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(10000L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestWithNonZeroBalanceAndBelowRMB() {
        long expected = -2L;
        account.setBalance(10000L); // $100.00
        account.setIsMinimumBalanceRequired(true);
        account.setRequiredMinimumBalance(50000L); // $500.00

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void calcComplexInterestWithZeroBalanceAndNoRMB() {
        long expected = -2L;
        account.setBalance(0L);
        account.setIsMinimumBalanceRequired(false);
        account.setRequiredMinimumBalance(0L);

        long actual = complexInterestCalculator.getInterestAmount();

        Assert.assertEquals(expected, actual);
    }
}
