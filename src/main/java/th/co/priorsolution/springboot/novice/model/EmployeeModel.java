package th.co.priorsolution.springboot.novice.model;

import lombok.Data;

@Data
public class EmployeeModel {
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String extension;
    private String email;
    private String officeCode;
    private String reportsTo;
    private String jobTitle;
}
