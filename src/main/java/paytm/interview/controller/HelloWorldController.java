package paytm.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sriramvcs on 2016-10-29.
 */
@Controller
public class HelloWorldController {

    @RequestMapping(path="/")
    public String index() {
        return "main";
    }
}
