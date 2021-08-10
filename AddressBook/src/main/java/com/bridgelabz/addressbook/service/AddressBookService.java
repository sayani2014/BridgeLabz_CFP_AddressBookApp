package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.AddressBookApplication;
import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.entity.AddressBook;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressBookService {
    public static final Logger logger = LoggerFactory.getLogger(AddressBookService.class);

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AddressBookDTO addAddressDetails(AddressBookDTO addressBookDTO) {
        logger.debug("Inside addAddressDetails()");
        AddressBook addressBookRequest = modelMapper.map(addressBookDTO, AddressBook.class);
        addressBookRepository.save(addressBookRequest);
        return addressBookDTO;
    }

    public List<AddressBookDTO> getAddressDetails() {
        logger.debug("Inside getAddressDetails()");
        return addressBookRepository.findAll().stream().map(addressBook -> {
            return new AddressBookDTO(addressBook.getId(), addressBook.getName(), addressBook.getAddress(),
                                                                addressBook.getPhoneNo(), addressBook.getEmail());
            }).collect(Collectors.toList());
    }

    public AddressBookDTO getAddressDetailsByID(int id) {
        logger.debug("Inside getAddressDetailsByID()");
        AddressBook addressBook = findAddressBookById(id);
        AddressBookDTO addressBookResponse = modelMapper.map(addressBook, AddressBookDTO.class);
        return addressBookResponse;
    }

    private AddressBook findAddressBookById(int id) {
        logger.debug("Inside findAddressBookById()");
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unable to find any address book detail!"));
    }

    public AddressBookDTO updateAddressDetails(int id, AddressBookDTO addressBookDTO) {
        logger.debug("Inside updateAddressDetails()");
        AddressBook addressBookRequest = modelMapper.map(addressBookDTO, AddressBook.class);
        AddressBookDTO addressBookResponse = null;
        if (id != 0) {
            AddressBook addressBookDetails = findAddressBookById(id);
            addressBookDetails.setAddress(addressBookRequest.getAddress());
            addressBookDetails.setPhoneNo(addressBookRequest.getPhoneNo());
            addressBookDetails.setEmail(addressBookRequest.getEmail());
            addressBookRepository.save(addressBookDetails);
            addressBookResponse = modelMapper.map(addressBookDetails, AddressBookDTO.class);
        }
        return addressBookResponse;
    }

    public String deleteAddressDetails(int id) {
        logger.debug("Inside deleteAddressDetails()");
        AddressBook addressBook = findAddressBookById(id);
        addressBookRepository.delete(addressBook);
        return "Address Book deleted successfully";
    }
}
