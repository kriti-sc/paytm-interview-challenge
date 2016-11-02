package paytm.interview.entity;

import lombok.Data;
import paytm.interview.enums.EmpType;

import javax.persistence.*;
import java.util.Calendar;


/**
 * Created by sriramvcs on 2016-10-31.
 */

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EmpType type;

    @Column(name="first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="emp_id")
    private Long empId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_ts")
    private Calendar createdTimeStamp;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_ts")
    private Calendar modifiedTimeStamp;

    public Employee(String firstName, String lastName, EmpType type, Long empId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.empId = empId;
        Calendar cal = Calendar.getInstance();
        this.createdTimeStamp = cal;
        this.modifiedTimeStamp = cal;
    }

    public Employee() {

    }



}
