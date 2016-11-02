package paytm.interview.dao;

import org.springframework.data.repository.CrudRepository;
import paytm.interview.entity.Review;

import java.util.List;

/**
 * Created by sriramvcs on 2016-10-31.
 */
public interface ReviewDao extends CrudRepository<Review,Long> {
    public List<Review> findByReviewer(Long id);
    public List<Review> findByReviewee(Long id);
}
