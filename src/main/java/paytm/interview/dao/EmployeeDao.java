package paytm.interview.dao;

import org.springframework.data.repository.CrudRepository;
import paytm.interview.entity.Employee;

import java.util.List;

/**
 * This Employee data access interface extends Spring Data's CRUD Repository.
 * The CRUD repo gives all the basic querying functionalities for free.
 * Created by sriramvcs on 2016-10-31.
 */
public interface EmployeeDao extends CrudRepository<Employee,Long> {
    List<Employee> findByFirstName(String firstName);
    Employee findByEmpId(Long empId);
}