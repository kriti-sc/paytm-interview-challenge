package paytm.interview.controller.rest;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import paytm.interview.domain.FeedbackDO;
import paytm.interview.enums.ReviewState;
import paytm.interview.service.ReviewService;

import java.util.List;

/**
 * Created by sriramvcs on 2016-11-07.
 */
@RestController
@RequestMapping("/feedback")
public class FeedbackCrudController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/view/{reviewId}", method = RequestMethod.GET)
    public List<FeedbackDO> getFeedbacksForReviewId(@PathVariable("reviewId") Long reviewId) {
        return reviewService.getFeedbacksForReviewId(reviewId);
    }

    @RequestMapping(value="/list/{empId}" ,method=RequestMethod.GET)
    public List<FeedbackDO> getFeedbackForEmpId(@PathVariable("empId") Long empId) {
        return reviewService.getAllFeedbacksForEmpId(empId);
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public void updateFeedback(@RequestBody MyFeedbackData feedbackData) {
        FeedbackDO feedbackDO = new FeedbackDO();
        feedbackDO.setContent(feedbackData.getContent());
        feedbackDO.setFeedbackId(feedbackData.getFeedbackId());
        if(feedbackData.isCompleted()) {
            feedbackDO.setState(ReviewState.Complete);
        }
        else {
            feedbackDO.setState(ReviewState.InComplete);
        }
        reviewService.updateFeedback(feedbackDO);
    }

    @RequestMapping(value="/{feedbackId}/detail", method = RequestMethod.GET)
    public FeedbackDO getFeedbackDetail(@PathVariable("feedbackId") Long feedbackId) {
        return (FeedbackDO) reviewService.getFeedbackDetail(feedbackId);
    }
    @Data
    private class MyFeedbackData {

        private String content;
        private Long feedbackId;
        private boolean completed;
    }

}
