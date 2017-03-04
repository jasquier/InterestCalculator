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
        account = new Account();
        account.setBalance(100000L); //$1000.00
        account.setAccountType("savings"); // this doesn't matter does it?
        account.setInterestRate(0.10); // 10% APY
        account.setOverDraftPenalty(0L); // when under $0.00 no interest
        account.setRequiredMinimumBalance(50000L); //$500.00 no interest when this hits


        interestCalculator = new InterestCalculator();
        interestCalculator.setAccount(account);

    }

    @Test
    public void calcSimpleInterestNormalNoRMB(){
        account.setIsMinimumBalanceRequired(false);
        interestCalculator.setInterestType(InterestType.SIMPLE);
        interestCalculator.setCalculationRule(CalculationRule.NONE);
        interestCalculator.setInterval(365);
        interestCalculator.calculateSimpleInterest();
        Assert.assertEquals(10000L, (long)interestCalculator.getInterestAmount());
    }

    @Test
    public void calcSimpleInterestNormalRMB(){
        account.setIsMinimumBalanceRequired(true);
        interestCalculator.setInterestType(InterestType.SIMPLE);
        interestCalculator.setCalculationRule(CalculationRule.NONE);
        interestCalculator.setInterval(365);
        interestCalculator.calculateSimpleInterest();
        Assert.assertEquals(10000L, (long)interestCalculator.getInterestAmount());
    }

    @Test
    public void calcSimpleInterestNormalBelowRMB(){
        account.setRequiredMinimumBalance(500000L); //$5000.00 no interest when this hits
        account.setIsMinimumBalanceRequired(true);
        interestCalculator.setInterestType(InterestType.SIMPLE);
        interestCalculator.setCalculationRule(CalculationRule.NONE);
        interestCalculator.setInterval(365);
        interestCalculator.calculateSimpleInterest();
        Assert.assertEquals(0L, (long)interestCalculator.getInterestAmount());
    }
}
