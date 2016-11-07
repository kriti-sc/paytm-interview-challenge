package paytm.interview.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.domain.EmployeeDO;
import paytm.interview.entity.Employee;
import paytm.interview.factory.EmployeeFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sriramvcs on 2016-11-05.
 */
@RestController
@RequestMapping("/employee")
public class EmployeeCrudRestController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeFactory empFactory;

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<EmployeeDO> getEmployeeList() {

        return buildEmpData(employeeDao.findAll());
    }

    /*@RequestMapping(value="/create", method = RequestMethod.POST)
    public void createEmployee(@@ModelAttribute EmpData empData) {

    }*/

    @RequestMapping(value="/delete", method = RequestMethod.DELETE)
    public void deleteEmployee(@RequestParam  Long id) {
        employeeDao.delete(id);
    }



    private List<EmployeeDO> buildEmpData(Iterable<Employee> empEntityList) {

        List<EmployeeDO> empDataList = new ArrayList<>();
        empEntityList.forEach(item->{
            empDataList.add(empFactory.createEmployeeDo(item));
        });
        return empDataList;
    }


}
