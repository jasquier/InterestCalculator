package team.squad.spring;

import org.springframework.web.bind.annotation.*;
import team.squad.accounts.Account;
import team.squad.builders.AccountBuilder;
import team.squad.interest.CalculationRule;
import team.squad.interest.InterestCalculator;
import team.squad.interest.InterestType;

import java.util.Map;

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
        return account;
    }

//    // Get account info GET
//    @CrossOrigin
//    @RequestMapping(value = "/getAccount", method = RequestMethod.GET, consumes = {"text/plain"})
//    public @ResponseBody Account getAccountInfo(String ID) {
//
//    }

    // Calculate interest POST
    @CrossOrigin
    @RequestMapping(value = "/interestCalculator", method = RequestMethod.POST, consumes = {"application/json"})
    public @ResponseBody
    InterestCalculator getInterestAmount(@RequestBody InterestCalculator interestCalculator) {
        return interestCalculator;
    }
}