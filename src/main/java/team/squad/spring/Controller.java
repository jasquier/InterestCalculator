package team.squad.spring;

import org.springframework.web.bind.annotation.*;
import team.squad.accounts.Account;
import team.squad.interest.InterestCalculator;

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

    // Calculate interest POST
    @CrossOrigin
    @RequestMapping(value = "/interestCalculator", method = RequestMethod.POST, consumes = {"application/json"})
    public @ResponseBody
    InterestCalculator getInterestAmount(@RequestBody InterestCalculator interestCalculator) {
        return interestCalculator;
    }
}