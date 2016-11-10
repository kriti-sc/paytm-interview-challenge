package paytm.interview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.domain.EmployeeDO;
import paytm.interview.service.EmployeeService;

/**
 * Created by sriramvcs on 2016-11-02.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(path="/employee/{empId}")
    public String getEmployeeView(@PathVariable Long empId, Model model){
        EmployeeDO empObj = (EmployeeDO) employeeService.getEmployeeByEmpId(empId);
        model.addAttribute("empId",empObj.getEmpId());
        model.addAttribute("empName",empObj.getLastName()+","+empObj.getFirstName());
        return "employee";
    }
}
