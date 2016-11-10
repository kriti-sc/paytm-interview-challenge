package paytm.interview.factory;

import org.springframework.stereotype.Component;
import paytm.interview.controller.rest.EmployeeCrudRestController;
import paytm.interview.domain.EmployeeDO;
import paytm.interview.entity.Employee;

import java.util.Calendar;

/**
 * Created by sriramvcs on 2016-11-05.
 */
@Component
public class EmployeeFactory {

    public Employee createEmployeeEntity(EmployeeDO obj) {
        Employee emp = new Employee();
        emp.setFirstName(obj.getFirstName());
        emp.setLastName(obj.getLastName());
        emp.setEmpId(obj.getEmpId());
        Calendar cal = Calendar.getInstance();
        emp.setCreatedTimeStamp(cal);
        emp.setModifiedTimeStamp(cal);
        return emp;
    }

    public EmployeeDO createEmployeeDo(Employee obj) {
        EmployeeDO emp = new EmployeeDO();
        emp.setFirstName(obj.getFirstName());
        emp.setLastName(obj.getLastName());
        emp.setEmpId(obj.getEmpId());
        emp.setType(obj.getType().toString());
        return emp;
    }
}
