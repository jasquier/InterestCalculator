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
    Account normalNoRMBAccount;
    Interval interval;

    @Before
    public void setup() {

        normalNoRMBAccount = new Account();
        normalNoRMBAccount = new Account();
        normalNoRMBAccount.setBalance(100000L); //$1000.00
        normalNoRMBAccount.setAccountType("savings"); // this doesn't matter does it?
        normalNoRMBAccount.setInterestRate(0.10); // 10% APY
        normalNoRMBAccount.setOverDraftPenalty(0L); // when under $0.00 no interest
        normalNoRMBAccount.setRequiredMinimumBalance(50000L); //$500.00 no interest when this hits


        interestCalculator = new InterestCalculator();
        interestCalculator.setAccount(normalNoRMBAccount);

    }

    @Test
    public void calcSimpleInterestNormalNoRMB(){
        normalNoRMBAccount.setIsMinimumBalanceRequired(false);
        interestCalculator.setInterestType(InterestType.SIMPLE);
        interestCalculator.setCalculationRule(CalculationRule.NONE);
        interestCalculator.setInterval(365);
        long actualInt = interestCalculator.calculateSimpleInterest();
        Assert.assertEquals(10000L, actualInt);
    }

    @Test
    public void calcSimpleInterestNormalRMB(){
        normalNoRMBAccount.setIsMinimumBalanceRequired(true);
        interestCalculator.setInterestType(InterestType.SIMPLE);
        interestCalculator.setCalculationRule(CalculationRule.NONE);
        interestCalculator.setInterval(365);
        long actualInt = interestCalculator.calculateSimpleInterest();
        Assert.assertEquals(10000L, actualInt);
    }
    
    
    
}
