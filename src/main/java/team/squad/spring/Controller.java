package team.squad.spring;

import org.springframework.web.bind.annotation.*;
import team.squad.accounts.Account;
import team.squad.builders.AccountBuilder;

/**
 * @author John A. Squier
 * add your name when you work on this file
 */
@RestController
public class Controller {

    // Create account POST
    @CrossOrigin
    @RequestMapping(value = "/createAccount", method = RequestMethod.POST, consumes = {"application/json"})
    public @ResponseBody
    Account createAccountAndGetID(@RequestBody Account account) {
        return AccountBuilder.createAccount();
    }

    // Get account info GET
    @CrossOrigin
    @RequestMapping(value = "/getAccount", method = RequestMethod.GET, consumes = {"text/plain"})
    public @ResponseBody Account getAccountInfo(String ID) {
        return Account.getAccountInfoByID(ID);
    }

    // Calculate interest GET
//    @CrossOrigin
//    @RequestMapping(value = "/interestCalculator", method = RequestMethod.GET, consumes = {"application/json"})
//    public @ResponseBody InterestCalculator getInterestAmount(String ID, Integer interval, Integer frequency,
//                                                              Long interestAmount, InterestType interestType,CalculationRule rule, Integer numDaysForRule) {
//        return InterestCalculatorBuilder.;
//    }
}