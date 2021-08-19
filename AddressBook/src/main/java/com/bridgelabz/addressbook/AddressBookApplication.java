/**
 * Use Lombok Library to auto generate getters and setters for the DTO.
 * Use Lombok Library for Logging.
 * Determine the Logging Levels, Logging to Console or File, Logging Patterns, etc based on this application running in
   Dev, Staging or Production.
 * Add Validation to Fields so the REST call can be validated.
 * Provide User Friendly Error Response in case validation fails.
 * Ability to throw User Friendly Errors in case Address Book Id is not found in Address Book App.
 *
 * @author : SAYANI KOLEY
 * @since : 11.08.2021
 */

package com.bridgelabz.addressbook;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class AddressBookApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
        log.info("Inside Main Method.");
    }
}
