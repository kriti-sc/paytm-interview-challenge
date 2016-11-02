package paytm.interview.entity;

import lombok.Data;
import paytm.interview.enums.ReviewState;

import javax.persistence.*;
import java.util.Calendar;


/**
 * Created by sriramvcs on 2016-10-31.
 */
@Data
@Entity
@Table(name="review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="reviewer_id")
    private Long reviewer;

    @Column(name="reviewee_id")
    private Long reviewee;

    @Column(name="content")
    private String content;

    @Column(name="state")
    @Enumerated(EnumType.STRING)
    private ReviewState state;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts")
    private Calendar createdTimeStamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_ts")
    private Calendar modifiedTimeStamp;

    public Review() {}

    public Review(Long reviewer,Long reviewee,ReviewState state) {
        this.reviewee = reviewee;
        this.reviewer = reviewer;
        this.state = state;
        Calendar cal = Calendar.getInstance();
        this.createdTimeStamp = cal;
        this.modifiedTimeStamp = cal;
    }


}
