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
    @Column(name = "employeeNumber")
    private String employeeNumber;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "extension")
    private String extension;

    @Column(name = "email")
    private String email;

    @Column(name = "officeCode")
    private String officeCode;

    @Column(name = "reportsTo")
    private String reportsTo;

    @Column(name = "jobTitle")
    private String jobTitle;
}
