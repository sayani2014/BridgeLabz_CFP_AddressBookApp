package com.bridgelabz.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
    private int id;

    @NotEmpty(message = "Person Name cannot be null")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]{2,}$", message = "Person Name Invalid")
    private String name;

    @NotEmpty(message = "Address cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9\\s,./]+$", message = "Person Address Invalid")
    private String address;

    @NotEmpty(message = "City cannot be null")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]+$", message = "Person City Invalid")
    private String city;

    @NotEmpty(message = "State cannot be null")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]+$", message = "Person State Invalid")
    private String state;

    @NotEmpty(message = "PhoneNo cannot be null")
    @Pattern(regexp = "^[0-9]{10}$", message = "Person PhoneNo Invalid")
    private String phoneNo;

    @NotEmpty(message = "Zip cannot be null")
    @Pattern(regexp = "^[0-9]{6}$", message = "Person Zip Invalid")
    private String zip;

    @NotEmpty(message = "Email cannot be null")
    @Email(message = "Person Email invalid")
    private String email;
}
