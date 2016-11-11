package paytm.interview.dao;

import paytm.interview.domain.FeedbackDO;

import java.util.List;

/**
 * This interface is used to access the Feedback table in the DB
 * Created by sriramvcs on 2016-11-07.
 */
public interface FeedbackDao {

    public void createFeedback(List<FeedbackDO> feebackObj);

    public List<FeedbackDO> findByReviewer(Long reviewerEmpId);

    public List<FeedbackDO> findByReviewId(Long reviewId);

    public void updateFeedback(FeedbackDO feedbackObj);

    public List<FeedbackDO> findById(Long feedbackId);
}
