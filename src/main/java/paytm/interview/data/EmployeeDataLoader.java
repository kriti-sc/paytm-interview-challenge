package paytm.interview.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.entity.Employee;
import paytm.interview.enums.EmpType;

/**
 * Created by sriramvcs on 2016-11-02.
 */
//@Component
public class EmployeeDataLoader implements CommandLineRunner {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public void run(String... strings) throws Exception {
        this.employeeDao.save(new Employee("John","Doe", EmpType.administrator,1000l));
        this.employeeDao.save(new Employee("Ramya","Krishnan", EmpType.staff,1001l));
    }
}
