/**
 * Create an Address Book Spring Project to cater to REST Request.
 * Create a Rest Controller to demonstrate the various HTTP Methods.
 * Introducing DTO and Model to AddressBook App.
 * Introducing Services Layer in AddressBook App.
 * Ability for the Services Layer to store the AddressBook Data.
 * Ability to log messages.
 *
 * @author : SAYANI KOLEY
 * @since : 10.08.2021
 */

package com.bridgelabz.addressbook;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AddressBookApplication {
    public static final Logger logger = LoggerFactory.getLogger(AddressBookApplication.class);

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        logger.debug("Welcome to Address Book Application.");
        logger.debug("Inside Main Method.");
        SpringApplication.run(AddressBookApplication.class, args);
    }

}
