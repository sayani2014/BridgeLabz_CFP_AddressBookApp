package com.bridgelabz.addressbook.builder;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.entity.AddressBookInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressBookBuilder {
    public AddressBookInfo buildDO(AddressBookDTO addressBookDTO) {
        AddressBookInfo addressBook = new AddressBookInfo();
        BeanUtils.copyProperties(addressBookDTO, addressBook);
        return addressBook;
    }
}
