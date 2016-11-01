package paytm.interview;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.entity.Employee;
import paytm.interview.enums.EmpType;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaytmInterviewChallengeApplicationTests {

	@Autowired
	private EmployeeDao employeeDao;

	@Test
	public void addEmployeeData() {
		Employee emp  = new Employee("Sriram","Chandrasekhar", EmpType.administrator);
		//employeeDao.save(emp);
		List<Employee> l = employeeDao.findByFirstName("Sriram");
		Assert.assertEquals(emp.getFirstName(),"Sriram");
	}

}
