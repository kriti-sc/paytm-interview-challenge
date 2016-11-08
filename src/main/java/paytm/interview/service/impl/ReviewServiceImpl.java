package paytm.interview.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import paytm.interview.dao.FeedbackDao;
import paytm.interview.dao.ReviewDao;
import paytm.interview.domain.FeedbackDO;
import paytm.interview.domain.ReviewDO;
import paytm.interview.service.ReviewService;
import java.util.List;

/**
 * Created by sriramvcs on 2016-11-07.
 */
@Service
public class ReviewServiceImpl implements ReviewService<ReviewDO,FeedbackDO> {

    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private FeedbackDao feedbackDao;

    @Override
    public boolean createReview(ReviewDO reviewObj) {
        reviewDao.createReview(reviewObj.getRevieweeEmpId());
        return true;
    }

    @Override
    public List<ReviewDO> getAllReviews() {
        List<ReviewDO> reviewList = reviewDao.findAllReviews();
        return reviewList;
    }

    @Override
    public boolean updateReview(ReviewDO reviewObj) {
        return false;
    }

    @Override
    public List<FeedbackDO> getAllFeedbacksForEmpId(Long empId) {
        return null;
    }

    @Override
    public boolean assignReviewees(List<Long> reviewerEmpIds,Long reviewId) {
        return false;
    }
}
