package com.bridgelabz.addressbook.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressBookDTO {
    private int id;
    @NotEmpty(message = "Person Name cannot be null")
    @Pattern(regexp = "^[A-Z][a-zA-z\\s]{2,}$", message = "Person Name Invalid")
    private String name;
    @Pattern(regexp = "^[A-Z][a-zA-z0-9\\s]{1,}$", message = "Person Address Invalid")
    private String address;
    @Pattern(regexp = "^[0-9]{10}$", message = "Person Phone Number Invalid")
    private String phoneNo;
    @Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" +
                            "@(?:([0-9-]{1}|[a-zA-Z]{3,5})\\.)+[a-zA-Z]{2,3}$", message = "Person Email Invalid")
    private String email;
}
