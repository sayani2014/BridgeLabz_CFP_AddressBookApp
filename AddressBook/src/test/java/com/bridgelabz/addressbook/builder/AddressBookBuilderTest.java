/**
 * Purpose : Ability to write Test Cases for AddressBookBuilder.class
 */

package com.bridgelabz.addressbook.builder;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.entity.AddressBookInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookBuilderTest {

    @InjectMocks
    private AddressBookBuilder addressBookBuilder;

    /**
     * Purpose : Ability to write Test Cases for buildDO() of AddressBookBuilder.class
                 Create an object of AddressBookDTO and initially set values to it.
                 Pass the object data to the buildDO() of AddressBookBuilder.class and store its return value.

     * Test : Check whether the property values matches with the expected values.
     */

    @Test
    public void buildDOTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Test");
        addressBookDTO.setAddress("12/1 abc");
        addressBookDTO.setCity("Test");
        addressBookDTO.setState("Test");
        addressBookDTO.setZip("789456");
        addressBookDTO.setPhoneNo("7894561230");
        addressBookDTO.setEmail("test@abc.com");

        AddressBookInfo addressBookInfo = addressBookBuilder.buildDO(addressBookDTO);

        Assert.assertEquals("Test", addressBookInfo.getName());
        Assert.assertEquals("12/1 abc", addressBookInfo.getAddress());
        Assert.assertEquals("Test", addressBookInfo.getCity());
        Assert.assertEquals("Test", addressBookInfo.getState());
        Assert.assertEquals("789456", addressBookInfo.getZip());
        Assert.assertEquals("7894561230", addressBookInfo.getPhoneNo());
        Assert.assertEquals("test@abc.com", addressBookInfo.getEmail());
    }
}
