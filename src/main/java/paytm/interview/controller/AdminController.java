package paytm.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller is used to return the thymeleaf template for admin views
 * Created by sriramvcs on 2016-11-02.
 */


@Controller
public class AdminController {

    @RequestMapping(path = "/admin")
    public String getAdminView(Model model) {
        model.addAttribute("empId",0);
        model.addAttribute("empName","Admin");
        return "main";
    }
}