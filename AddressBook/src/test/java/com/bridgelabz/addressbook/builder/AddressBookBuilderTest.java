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
        addressBookDTO.setAddress("WB");
        addressBookDTO.setPhoneNo("456789");
        addressBookDTO.setEmail("abc@abc.com");

        AddressBookInfo addressBookInfo = addressBookBuilder.buildDO(addressBookDTO);

        Assert.assertEquals("Test", addressBookInfo.getName());
        Assert.assertEquals("WB", addressBookInfo.getAddress());
        Assert.assertEquals("456789", addressBookInfo.getPhoneNo());
        Assert.assertEquals("abc@abc.com", addressBookInfo.getEmail());
    }
}
