package paytm.interview.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by sriramvcs on 2016-11-05.
 */
@Data
@Component
public class EmployeeDO {

    private String firstName;
    private String lastName;
    private Long empId;
    private String type;
}
