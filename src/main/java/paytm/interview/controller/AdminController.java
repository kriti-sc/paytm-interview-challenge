package paytm.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by sriramvcs on 2016-11-02.
 */
@Controller
public class AdminController {

    @RequestMapping(path = "/admin")
    public String getAdminView() {
        return "admin";
    }
}