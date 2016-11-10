package paytm.interview.controller.rest.data;

import lombok.Data;

/**
 * Created by sriramvcs on 2016-11-10.
 */
@Data
public class MyFeedbackData {

    private String content;
    private Long feedbackId;
    private boolean completed;

    public MyFeedbackData(String content, Long feedbackId, boolean completed) {
        this.content = content;

        this.feedbackId = feedbackId;
        this.completed = completed;
    }

    public MyFeedbackData() {

    }


}