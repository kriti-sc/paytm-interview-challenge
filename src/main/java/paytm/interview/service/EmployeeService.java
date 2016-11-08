package paytm.interview.service;

import paytm.interview.domain.EmployeeDO;

import java.util.List;

/**
 * Created by sriramvcs on 2016-11-07.
 */
public interface EmployeeService<E> {

    public boolean createEmployee(E empObj);

    public boolean updateEmployee(E empObj);

    public List<E> getAllEmployees();

    public E getEmployeeByEmpId(Long empId);

}
