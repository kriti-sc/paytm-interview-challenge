package paytm.interview.service;

import paytm.interview.domain.EmployeeDO;

import java.util.List;

/**
 * This is a generic interface service that provides
 * all application related functionality for handling employees
 * Created by sriramvcs on 2016-11-07.
 */
public interface EmployeeService<E> {

    public void createEmployee(E empObj);

    public void updateEmployee(E empObj);

    public List<E> getAllEmployees();

    public E getEmployeeByEmpId(Long empId);

}
