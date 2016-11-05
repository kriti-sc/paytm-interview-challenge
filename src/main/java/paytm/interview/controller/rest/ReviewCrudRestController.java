package paytm.interview.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import paytm.interview.dao.ReviewDao;

/**
 * Created by sriramvcs on 2016-11-05.
 */
@RestController
@RequestMapping("/review")
public class ReviewCrudRestController {

    @Autowired
    private ReviewDao reviewDao;

    public void getAllReviews() {

    }
}
