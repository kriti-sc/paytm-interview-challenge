package paytm.interview.service;

import paytm.interview.domain.ReviewDO;

import java.util.List;

/**
 * Created by sriramvcs on 2016-11-07.
 * In the interface T in the Review domain object and V is the feedback domain object.
 * Review and feedback share a one to many relationship. 1 review can have multiple feedbacks.
 */
public interface ReviewService<T,V> {

    public boolean createReview(T reviewObj);

    public List<T> getAllReviews();

    public boolean updateReview(T reviewObj);

    public List<V> getAllFeedbacksForEmpId(Long empId);

    public boolean assignReviewees(List<Long> feedbackObjs,Long reviewId);

}
