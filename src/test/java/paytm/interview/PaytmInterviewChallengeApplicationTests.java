package paytm.interview;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import paytm.interview.dao.EmployeeDao;
import paytm.interview.dao.FeedbackDao;
import paytm.interview.dao.ReviewDao;
import paytm.interview.domain.FeedbackDO;
import paytm.interview.domain.ReviewDO;
import paytm.interview.entity.Employee;
import paytm.interview.enums.EmpType;
import paytm.interview.enums.ReviewState;
import java.util.List;

/*
Integration tests. Used for dev purposes only.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
public class PaytmInterviewChallengeApplicationTests {

	@Autowired
	private EmployeeDao employeeDao;

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private FeedbackDao feedbackDao;

	//@Test
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
		/*e = employeeDao.findByEmpId(1002l);
		employeeDao.delete(e.getId());
		e = employeeDao.findByEmpId(1002l);
		Assert.assertEquals(e,null);*/
	}

	//@Test
	public void reviewCrudTests() {
		// create an employee with emp_id 1001
		//reviewDao.createReview(1004l);
		List<ReviewDO> res = reviewDao.findByRevieweeEmpId(1004l);
		Assert.assertEquals(1,res.size());

		List<ReviewDO> res1 = reviewDao.findAllReviews();
		Assert.assertEquals(res1.size(),3);
	}

	//@Test
	public void feedbackCrudTests() {
//		List<FeedbackDO> feedbackList = new ArrayList<>();
//		//review_id,reviewer_emp_id,reviewee_emp_id
//		FeedbackDO f1 = new FeedbackDO();
//		f1.setReviewId(3l);
//		f1.setReviewerId(1002l);
//		f1.setRevieweeId(1004l);
//		feedbackList.add(f1);
//		FeedbackDO f2 = new FeedbackDO();
//		f2.setReviewId(3l);
//		f2.setReviewerId(1003l);
//		f2.setRevieweeId(1004l);
//		feedbackList.add(f2);
//		feedbackDao.createFeedback(feedbackList);
		List<FeedbackDO> l = feedbackDao.findByReviewId(3l);
		Assert.assertEquals(l.size(),2);
		List<FeedbackDO> l1 = feedbackDao.findByReviewer(1003l);
		Assert.assertEquals(l1.size(),2);

		// update
		FeedbackDO f1 = new FeedbackDO();
		f1.setFeedbackId(4l);
		f1.setState(ReviewState.Complete);
		f1.setContent("This review is complete");
		feedbackDao.updateFeedback(f1);

	}

//	@After
//	public void cleanUp() {
//		employeeDao.deleteAll();
//		reviewDao.deleteAll();
//	}



}
