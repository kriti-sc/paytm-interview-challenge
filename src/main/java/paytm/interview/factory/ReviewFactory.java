package paytm.interview.factory;

import org.springframework.stereotype.Component;
import paytm.interview.domain.FeedbackDO;
import paytm.interview.domain.ReviewDO;
import paytm.interview.enums.ReviewState;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sriramvcs on 2016-11-06.
 * This factory builds domain objects for reviews and feedbacks from jdbc result sets.
 */
@Component
public class ReviewFactory {

    /**
     * Builds Review domain objects from the result set.
     * @param resultList
     * @return ReviewDO
     */
    public List<ReviewDO> returnReviewDoList(List<Map<String, Object>> resultList) {
        List<ReviewDO> reviewDoList = new ArrayList<>();
        resultList.forEach(item->reviewDoList.add(buildReviewFromResultSet(item)));

        return reviewDoList;
    }


    private ReviewDO buildReviewFromResultSet(Map<String, Object> item) {

        ReviewDO obj = new ReviewDO();

        try {
            if(item.get("id")!=null) {
                obj.setReviewId((Long) item.get("id"));
            }
            if(item.get("reviewee_id")!=null) {
                obj.setRevieweeEmpId((Long) item.get("reviewee_id"));
            }
            StringBuilder fullName = new StringBuilder();

            if(item.get("last_name")!=null) {
                fullName.append(item.get("last_name")+" ");
            }

            if(item.get("first_name")!=null) {
                fullName.append((String)item.get("first_name"));
            }
            obj.setRevieweName(fullName.toString());

            obj.setState(ReviewState.InComplete); //TODO need to query the DB for this info

        }catch(Exception e) {
            e.printStackTrace();
        }

        return obj;

    }

    /**
     * Build feedback domain object from result set.
     * @param resultList
     * @return
     */
    public List<FeedbackDO> returnFeedbackDoList(List<Map<String, Object>> resultList) {
        List<FeedbackDO> feedbackDoList = new ArrayList<>();
        resultList.forEach(item->feedbackDoList.add(buildFeedbackFromResultSet(item)));

        return feedbackDoList;

    }

    private FeedbackDO buildFeedbackFromResultSet(Map<String, Object> item) {
        FeedbackDO obj = new FeedbackDO();

        try {
            if(item.get("review_id")!=null) {
                obj.setReviewId((Long) item.get("review_id"));
            }
            if(item.get("reviewee_id")!=null) {
                obj.setRevieweeId((Long) item.get("reviewee_id"));
            }
            StringBuilder fullName = new StringBuilder();

            if(item.get("last_name")!=null) {
                fullName.append(item.get("last_name")+" ");
            }

            if(item.get("first_name")!=null) {
                fullName.append((String)item.get("first_name"));
            }
            obj.setRevieweeName(fullName.toString());

            if(item.get("state")!=null) {
                String st = (String)item.get("state");
                if(st.equalsIgnoreCase("Complete")) {
                    obj.setState(ReviewState.Complete);
                }
                else
                    obj.setState(ReviewState.InComplete);
            }

            if(item.get("content")!=null) {
                obj.setContent((String)item.get("content"));
            }

            if(item.get("id")!=null) {
                obj.setFeedbackId((Long) item.get("id"));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
