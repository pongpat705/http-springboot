package th.co.priorsolution.springboot.novice.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
@Data
public class EmployeeEntity {
    @Id
    @Column(name = "EMPLOYEE_NUMBER")
    private String employeeNumber;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EXTENSION")
    private String extension;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "OFFICE_CODE")
    private String officeCode;

    @Column(name = "REPORTS_TO")
    private String reportsTo;

    @Column(name = "JOB_TITLE")
    private String jobTitle;
}
