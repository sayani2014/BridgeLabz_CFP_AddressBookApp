/**
 * Purpose : Ability to write Test Cases for AddressBookController.class
 */

package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.dto.ResponseDTO;
import com.bridgelabz.addressbook.service.AddressBookService;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;
    @Mock
    private AddressBookService iAddressBookService;

    /**
     * Purpose : Ability to write Test Cases for getAddressesDetails() of AddressBookController.class
                 Using the Mockito framework, when the iAddressBookService.getAddressDetails() is called,
                 then return a list of AddressBookDTO object.
                 Store the return value of addressBookController.getAddressDetails() in a variable of ResponseEntity<ResponseDTO>.

     * Test : Check whether the responseEntity value is null or not.
              Check whether the status code of responseEntity matches with the expected value.
              Check whether the message of the responseEntity body matches with the expected value.
              Check whether the responseEntity body data is null or not.
     */

    @Test
    public void getAddressesDetailsTest() {
        when(iAddressBookService.getAddressDetails()).thenReturn(Lists.newArrayList(new AddressBookDTO()));
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.getAddressDetails();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched all Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /**
     * Purpose : Ability to write Test Cases for addAddressDetails() of AddressBookController.class
                 Create an object  of the AddressBookDTO and set values to it.
                 Using the Mockito framework, when the iAddressBookService.addAddressDetails() is called,
                 then return the AddressBookDTO object.
                 Store the return value of addressBookController.addAddressDetails() in a variable of ResponseEntity<ResponseDTO>.

     * Test : Check whether the responseEntity value is null or not.
              Check whether the status code of responseEntity matches with the expected value.
              Check whether the message of the responseEntity body matches with the expected value.
              Check whether the responseEntity body data is null or not.
     */

    @Test
    public void addAddressDetailsTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("WB");
        addressBookDTO.setPhoneNo("123456");
        addressBookDTO.setEmail("abc@abc.in");

        when(iAddressBookService.addAddressDetails(addressBookDTO)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.addAddressDetails(addressBookDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Added Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /**
     * Purpose : Ability to write Test Cases for updateAddressDetails() of AddressBookController.class
                 Create an object  of the AddressBookDTO and set values to it.
                 Using the Mockito framework, when the iAddressBookService.updateAddressDetails() is called,
                 then return the AddressBookDTO object.
                 Store the return value of addressBookController.deleteAddressDetails() in a variable of ResponseEntity<ResponseDTO>.

     * Test : Check whether the responseEntity value is null or not.
              Check whether the status code of responseEntity matches with the expected value.
              Check whether the message of the responseEntity body matches with the expected value.
              Check whether the responseEntity body data is null or not.
     */

    @Test
    public void updateAddressDetailsTest() {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("WB");
        addressBookDTO.setPhoneNo("123456");
        addressBookDTO.setEmail("abc@abc.in");

        when(iAddressBookService.updateAddressDetails(id, addressBookDTO)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.updateAddressDetails(id, addressBookDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated by ID : Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /**
     * Purpose : Ability to write Test Cases for deleteAddressDetails() of AddressBookController.class
                 Using the Mockito framework, when the iAddressBookService.deleteAddressDetails() is called,
                 then return the AddressBookDTO object.
                 Store the return value of addressBookController.updateAddressDetails() in a variable of ResponseEntity<ResponseDTO>.

     * Test : Check whether the responseEntity value is null or not.
              Check whether the status code of responseEntity matches with the expected value.
              Check whether the message of the responseEntity body matches with the expected value.
              Check whether the responseEntity body data is null or not.
     */

    @Test
    public void deleteAddressDetailsTest() {
        int id = 1;

        when(iAddressBookService.deleteAddressDetails(id)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.deleteAddressDetails(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted by ID : Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }

    /**
     * Purpose : Ability to write Test Cases for getAddressDetailsByID() of AddressBookController.class
                 Using the Mockito framework, when the iAddressBookService.getAddressDetailsByID() is called,
                 then return the AddressBookDTO object.
                 Store the return value of addressBookController.getAddressDetailsByID() in a variable of ResponseEntity<ResponseDTO>.

     * Test : Check whether the responseEntity value is null or not.
              Check whether the status code of responseEntity matches with the expected value.
              Check whether the message of the responseEntity body matches with the expected value.
              Check whether the responseEntity body data is null or not.
     */

    @Test
    public void getAddressDetailsByIDTest() {
        int id = 1;

        when(iAddressBookService.getAddressDetailsByID(id)).thenReturn(new AddressBookDTO());
        ResponseEntity<ResponseDTO> responseEntity = addressBookController.getAddressDetailsByID(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched by ID : Address Book Details", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getData());
    }
}
