package paytm.interview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.domain.EmployeeDO;
import paytm.interview.entity.Employee;
import paytm.interview.factory.EmployeeFactory;
import paytm.interview.service.EmployeeService;

import java.util.List;

/**
 * Created by sriramvcs on 2016-11-07.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService<EmployeeDO> {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeFactory employeeFactory;
    @Override
    public boolean createEmployee(EmployeeDO empObj) {
        return false;
    }

    @Override
    public boolean updateEmployee(EmployeeDO empObj) {
        return false;
    }

    @Override
    public List<EmployeeDO> getAllEmployees() {
        return null;
    }

    @Override
    public EmployeeDO getEmployeeByEmpId(Long empId) {
        Employee emp = employeeDao.findByEmpId(empId);
        return employeeFactory.createEmployeeDo(emp);
    }
}
