package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.builder.AddressBookBuilder;
import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.entity.AddressBookInfo;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressBookBuilder addressBuilder;

    /**
     * Purpose : Ability to add person details in Address Book
     * @param addressBookDTO
     * @return
     */

    public AddressBookDTO addAddressDetails(AddressBookDTO addressBookDTO) {
        log.info("Inside addAddressDetails()");
        AddressBookInfo addressBookDO = addressBuilder.buildDO(addressBookDTO);
        addressBookDO = addressBookRepository.save(addressBookDO);
        addressBookDTO.setId(addressBookDO.getId());
        return addressBookDTO;
    }

    /**
     * Purpose : Ability to fetch all person details from Address Book
     * @return
     */

    public List<AddressBookDTO> getAddressDetails() {
        log.info("Inside getAddressDetails()");
        return addressBookRepository.findAll().stream().map(addressBook -> {
            return new AddressBookDTO(addressBook.getId(), addressBook.getName(), addressBook.getAddress(),
                                                                addressBook.getPhoneNo(), addressBook.getEmail());
            }).collect(Collectors.toList());
    }

    /**
     * Purpose : Ability to fetch person details from Address Book using ID
     * @param id
     * @return
     */

    public AddressBookDTO getAddressDetailsByID(int id) {
        log.info("Inside getAddressDetailsByID()");
        AddressBookInfo addressBook = findAddressBookById(id);
        AddressBookDTO addressBookResponse = modelMapper.map(addressBook, AddressBookDTO.class);
        return addressBookResponse;
    }

    /**
     * Purpose : Ability to find person details from Address Book using ID
     * @param id
     * @return
     */

    private AddressBookInfo findAddressBookById(int id) {
        log.info("Inside findAddressBookById()");
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new AddressBookException("Unable to find any address book detail!"));
    }

    /**
     * Purpose : Ability to update person details in Address Book using ID
     * @param id
     * @param addressBookDTO
     * @return
     */

    public AddressBookDTO updateAddressDetails(int id, AddressBookDTO addressBookDTO) {
        log.info("Inside updateAddressDetails()");
        AddressBookInfo addressBookDetails = findAddressBookById(id);
        String[] ignoreFields = {"id", "name"};
        BeanUtils.copyProperties(addressBookDTO, addressBookDetails, ignoreFields);
        addressBookRepository.save(addressBookDetails);

        return addressBookDTO;
    }

    /**
     * Purpose : Ability to delete person details from Address Book using ID
     * @param id
     * @return
     */

    public AddressBookDTO deleteAddressDetails(int id) {
        log.info("Inside deleteAddressDetails()");
        AddressBookInfo addressBook = findAddressBookById(id);
        addressBookRepository.delete(addressBook);

        return null;
    }
}
