package paytm.interview.controller.rest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.entity.Employee;

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

    @RequestMapping(value="/list", method = RequestMethod.GET)
    public List<EmpData> getEmployeeList() {
        return buildEmpData(employeeDao.findAll());
    }

    @RequestMapping(value="/create", method = RequestMethod.POST)
    public void createEmployee() {

    }



    private List<EmpData> buildEmpData(Iterable<Employee> empEntityList) {

        List<EmpData> empDataList = new ArrayList<>();
        empEntityList.forEach(item->{
            EmpData data = new EmpData();
            data.setFirstName(item.getFirstName());
            data.setLastName(item.getFirstName());
            data.setEmpId(item.getEmpId());
            data.setType(item.getType().name());
            empDataList.add(data);
        });
        return empDataList;
    }

    @Data
    private class EmpData {
        private String firstName;
        private String lastName;
        private Long empId;
        private String type;
    }
}
