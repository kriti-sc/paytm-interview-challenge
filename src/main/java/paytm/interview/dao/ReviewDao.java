package paytm.interview.dao;

import paytm.interview.domain.ReviewDO;
import java.util.List;

/**
 * This interface is used to access the Feedback table in the DB
 * Created by sriramvcs on 2016-10-31.
 */
public interface ReviewDao {

    public List<ReviewDO> findByRevieweeEmpId(Long id);

    public List<ReviewDO> findAllReviews();

    public void createReview(Long revieweeId);

}
