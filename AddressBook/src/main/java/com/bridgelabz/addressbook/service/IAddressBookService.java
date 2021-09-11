package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.dto.StateDTO;

import java.util.List;

public interface IAddressBookService {

    AddressBookDTO addAddressDetails(AddressBookDTO addressBookDTO);

    List<AddressBookDTO> getAddressDetails();

    AddressBookDTO getAddressDetailsByID(int id);

    AddressBookDTO updateAddressDetails(int id, AddressBookDTO addressBookDTO);

    AddressBookDTO deleteAddressDetails(int id);

    List<StateDTO> getStateDetails();
}
