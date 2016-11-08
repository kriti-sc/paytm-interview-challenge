package paytm.interview.domain;

import lombok.Data;
import paytm.interview.enums.ReviewState;

/**
 * Created by sriramvcs on 2016-11-07.
 */
@Data
public class FeedbackDO {

    private Long feedbackId;
    private Long reviewId;
    private Long revieweeId;
    private Long reviewerId;
    private String revieweeName;
    private String content;
    private ReviewState state;
}
