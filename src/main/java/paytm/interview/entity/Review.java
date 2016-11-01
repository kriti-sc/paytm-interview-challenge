package paytm.interview.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Created by sriramvcs on 2016-10-31.
 */
@Data
@Entity(name="entity")
public class Review {

    @Id
    @GeneratedValue
    private String id;
    private Long reviewId;
    private Long reviewer;
    private Long reviewee;

    public Review(String id, Long reviewId, Long reviewer, Long reviewee) {
        this.id = id;
        this.reviewId = reviewId;
        this.reviewer = reviewer;
        this.reviewee = reviewee;
    }


}
