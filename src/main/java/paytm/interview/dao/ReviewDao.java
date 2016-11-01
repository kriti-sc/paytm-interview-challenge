package paytm.interview.dao;

import org.springframework.data.repository.CrudRepository;
import paytm.interview.entity.Review;

/**
 * Created by sriramvcs on 2016-10-31.
 */
public interface ReviewDao extends CrudRepository<Review,Long> {
}
