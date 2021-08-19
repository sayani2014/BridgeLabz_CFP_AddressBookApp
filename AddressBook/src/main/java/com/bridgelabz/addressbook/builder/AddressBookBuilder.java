package com.bridgelabz.addressbook.builder;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.entity.AddressBookInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressBookBuilder {

    /**
     * Purpose : Ability to insert person details in Address Book.
     *
     * @param addressBookDTO Object of AddressBookDTO which will validate user-input
     *                       and once valid, will pass it to the AddressBookInfo entity.
     *
     * @return addressBook Object of AddressBook.
     */

    public AddressBookInfo buildDO(AddressBookDTO addressBookDTO) {
        AddressBookInfo addressBook = new AddressBookInfo();
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        return addressBook;
    }
}
