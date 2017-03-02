package team.squad;

import org.springframework.web.bind.annotation.*;

/**
 * @author John A. Squier
 * add your name when you work on this
 */
@RestController
public class Controller {

    @CrossOrigin
    @RequestMapping(value = "/interestCalculator", method = RequestMethod.POST, consumes = {"application/json"})
    public @ResponseBody InterestCalculator getInterestAmount(@RequestBody InterestCalculator interestCalculator) {
        return interestCalculator;
    }
}