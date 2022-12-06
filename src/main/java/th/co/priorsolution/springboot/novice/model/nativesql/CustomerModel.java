package th.co.priorsolution.springboot.novice.model.nativesql;

import lombok.Data;

@Data
public class CustomerModel {
    private String firstName;
    private String lastName;
    private String email;
    private String active;
    private String createDate;
    private String address;
    private String address2;
    private String district;
    private String postalCode;
    private String phone;
    private String location;
    private String city;
    private String country;
}
