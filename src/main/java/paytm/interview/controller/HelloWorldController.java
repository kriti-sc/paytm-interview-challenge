package paytm.interview.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sriramvcs on 2016-10-29.
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "Hello world paytm!!";
    }
}
