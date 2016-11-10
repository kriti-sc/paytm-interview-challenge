package paytm.interview.controller.rest.data;

import lombok.Data;

/**
 * Created by sriramvcs on 2016-11-10.
 */
@Data
public class JsonResponse {

    private String status;
    private String message;

    public JsonResponse(String status,String message) {
        this.status = status;
        this.message = message;
    }
}
