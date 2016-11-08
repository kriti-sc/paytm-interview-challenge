package paytm.interview.domain;

import lombok.Data;
import paytm.interview.enums.ReviewState;

/**
 * Created by sriramvcs on 2016-11-05.
 */
@Data
public class ReviewDO {

    private Long reviewId;
    private Long revieweeEmpId;
    private String revieweName;
    private ReviewState state;

}
