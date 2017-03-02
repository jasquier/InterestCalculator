package team.squad;

import org.junit.Before;
import org.junit.Test;

/**
 * @author John A. Squier
 * add your name when you work on this file.
 */
public class InterestCalculatorTests {

    InterestCalculator interestCalculator;
    Account normalNoRMBAccount;

    @Before
    public void setup() {
        interestCalculator = new InterestCalculator();
        normalNoRMBAccount = new Account();
        normalNoRMBAccount = new Account();
        normalNoRMBAccount.setBalance(100000L); //$1000.00
        normalNoRMBAccount.setAccountType("savings"); // this doesn't matter does it?
        normalNoRMBAccount.setInterestRate(0.10); // 10% APY
        normalNoRMBAccount.setOverDraftPenalty(0L); // when under $0.00 no interest
        normalNoRMBAccount.setRequiredMinimumBalance(50000L); //$500.00 no interest when this hits
        normalNoRMBAccount.setIsMinimumBalanceRequired(false);
    }

    @Test
    public void calcSimpleInterestNormalNoRMB(){

    }
    
    
    
}
