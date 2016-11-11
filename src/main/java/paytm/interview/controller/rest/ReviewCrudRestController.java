package paytm.interview.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paytm.interview.dao.ReviewDao;
import paytm.interview.domain.FeedbackDO;
import paytm.interview.domain.ReviewDO;
import paytm.interview.service.ReviewService;

import java.util.List;

/**
 * Created by sriramvcs on 2016-11-05.
 */
@RestController
public class ReviewCrudRestController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value="/reviews", method = RequestMethod.GET)
    public List<ReviewDO> getAllReviews() {

        return reviewService.getAllReviews();
    }

    @RequestMapping(value="/reviews/create/{revieweeId}" ,method=RequestMethod.POST)
    public void createReview(@RequestParam(required = true,value = "revieweeId") Long revieweeId) {
        reviewService.createReview(revieweeId);
    }

    @RequestMapping(value="/reviews/{reviewId}/feedbacks" ,method=RequestMethod.GET)
    public List<FeedbackDO> getFeedbackForReviewId(@PathVariable("reviewId") Long reviewId) {
        return reviewService.getFeedbacksForReviewId(reviewId);
    }


    @RequestMapping(value="/reviews/{reviewId}/feedbacks/assign" ,method=RequestMethod.POST)
    public List<FeedbackDO> assignFeedbackToReviewer(@PathVariable("reviewId") Long reviewId,
                                                     @RequestParam(required = true,value = "reviewerIdList")List<Long> reviewerIdList) {
        return reviewService.getFeedbacksForReviewId(reviewId);
    }

}
