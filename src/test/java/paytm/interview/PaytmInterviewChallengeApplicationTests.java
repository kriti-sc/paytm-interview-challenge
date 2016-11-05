package paytm.interview;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.junit4.SpringRunner;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.dao.ReviewDao;
import paytm.interview.entity.Employee;
import paytm.interview.entity.Review;
import paytm.interview.enums.EmpType;
import paytm.interview.enums.ReviewState;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaytmInterviewChallengeApplicationTests {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ReviewDao reviewDao;

	@Test
	public void employeeCrudTests() {
		// create
		Employee emp = new Employee("Ramya","Krishnan",EmpType.staff,1002l);
		employeeDao.save(emp);
		List<Employee> l = employeeDao.findByFirstName("Ramya");
		Employee e = employeeDao.findByEmpId(1002l);
		Assert.assertEquals(l.size(),1);
		// read
		Assert.assertEquals(e.getFirstName(),"Ramya");
		// update
		e.setFirstName("Sriram");
		employeeDao.save(e);
		Assert.assertEquals(employeeDao.findByEmpId(1002l).getFirstName(),"Sriram");

		// delete
		e = employeeDao.findByEmpId(1002l);
		employeeDao.delete(e.getId());
		e = employeeDao.findByEmpId(1002l);
		Assert.assertEquals(e,null);
	}

	@Test
	public void reviewCrudTests() {

		Employee emp = new Employee("Ramya","Krishnan",EmpType.staff,1002l);
		employeeDao.save(emp);
		emp = new Employee("Sriram","Chandrasekhar",EmpType.staff,1003l);
		employeeDao.save(emp);
		// add admin
		emp = new Employee("John","Doe",EmpType.administrator,1000l);
		employeeDao.save(emp);

		// create
		Employee reviewer = employeeDao.findByEmpId(1003l);
		Employee reviewee  = employeeDao.findByEmpId(1002l);
		Review rev = new Review(reviewer.getId(),reviewee.getId(), ReviewState.Assigned);
		reviewDao.save(rev);
		// read
		List<Review> resList1 =  reviewDao.findByReviewee(reviewee.getId());
		List<Review> resList2 = reviewDao.findByReviewer(reviewer.getId());
		Assert.assertEquals(resList1.size(),resList2.size());

	}

	@After
	public void cleanUp() {
		employeeDao.deleteAll();
		reviewDao.deleteAll();
	}



}
