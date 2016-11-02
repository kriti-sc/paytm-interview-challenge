package paytm.interview.dao;

import org.springframework.data.repository.CrudRepository;
import paytm.interview.entity.Employee;

import java.util.List;

/**
 * Created by sriramvcs on 2016-10-31.
 */
public interface EmployeeDao extends CrudRepository<Employee,Long> {
    List<Employee> findByFirstName(String firstName);
    Employee findByEmpId(Long empId);
}