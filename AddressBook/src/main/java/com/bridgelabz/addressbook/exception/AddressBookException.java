package com.bridgelabz.addressbook.exception;

public class AddressBookException extends RuntimeException{

    /**
     * Purpose : Ability to handle RuntimeException if the user-defined ID is not found in the DB
     * @param message
     */

    public AddressBookException(String message) {
        super(message);
    }
}
