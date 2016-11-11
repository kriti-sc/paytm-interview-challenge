package paytm.interview.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.domain.EmployeeDO;
import paytm.interview.entity.Employee;
import paytm.interview.factory.EmployeeFactory;
import paytm.interview.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

/**
 * This controller is used to serve up all get/post AJAX data requests related to employees
 * Created by sriramvcs on 2016-11-05.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeCrudRestController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<EmployeeDO> getEmployeeList() {

        return employeeService.getAllEmployees();
    }

    /*@RequestMapping(value="/create", method = RequestMethod.POST)
    public void createEmployee(@@ModelAttribute EmpData empData) {

    }*/




}
