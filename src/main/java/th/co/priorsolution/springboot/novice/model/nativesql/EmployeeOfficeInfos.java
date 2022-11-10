package th.co.priorsolution.springboot.novice.model.nativesql;

import lombok.Data;

@Data
public class EmployeeOfficeInfos {
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String territory;
    private String country;
    private String state;
    private String phone;
    private String postalCode;
}
