package paytm.interview.entity;

import lombok.Data;
import paytm.interview.enums.EmpType;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Created by sriramvcs on 2016-10-31.
 */

@Entity(name="employee")
@Data
public class Employee {

    @Id
    @GeneratedValue
    private  Long id;
    @Enumerated
    private EmpType type;
    private String firstName;
    private String lastName;
    private Long empId;

    public Employee(String firstName, String lastName,EmpType type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[firstName='%s', lastName='%s']",
                firstName, lastName);
    }
}
