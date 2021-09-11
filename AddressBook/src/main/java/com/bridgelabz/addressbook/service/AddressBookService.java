package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.builder.AddressBookBuilder;
import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.dto.StateDTO;
import com.bridgelabz.addressbook.entity.AddressBookInfo;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.repository.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AddressBookService implements IAddressBookService{

    @Autowired
    private AddressBookRepository addressBookRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AddressBookBuilder addressBuilder;

    /**
     * Purpose : Ability to insert person details in Address Book.
     *
     * @param addressBookDTO Object of AddressBookDTO which will validate user-input
     *                       and once valid, will pass it to the AddressBook entity.
     *                       This operation takes place in the AddressBookBuilder.class which returns the object of
     *                       AddressBookInfo.class. Finally, the user-input details gets stored in the Database.
     *
     * @return addressBookDTO Object of AddressBookDTO.
     */

    @Override
    public AddressBookDTO addAddressDetails(AddressBookDTO addressBookDTO) {
        log.info("Inside addAddressDetails()");
        AddressBookInfo addressBookDO = addressBuilder.buildDO(addressBookDTO);
        addressBookDO = addressBookRepository.save(addressBookDO);
        addressBookDTO.setId(addressBookDO.getId());

        return addressBookDTO;
    }

    /**
     * Purpose : Ability to fetch all person details from Address Book.
     *
     * @return List<AddressBookDTO>.
     */

    @Override
    public List<AddressBookDTO> getAddressDetails() {
        log.info("Inside getAddressDetails()");
        return addressBookRepository.findAll().stream().map(addressBook -> {
            return new AddressBookDTO(addressBook.getId(), addressBook.getName(), addressBook.getAddress(),
                    addressBook.getCity(), addressBook.getState(), addressBook.getZip(), addressBook.getPhoneNo());
            }).collect(Collectors.toList());
    }

    /**
     * Purpose : Ability to fetch person details from Address Book based on a particular ID.
     *
     * @param id On providing ID, the user-input is matched with the id value of the database.
     *           If found, it returns the person details from Address Book, else returns error message.
     *
     * @return addressBookResponse Object of AddressBookDTO.
     */

    @Override
    public AddressBookDTO getAddressDetailsByID(int id) {
        log.info("Inside getAddressDetailsByID()");
        AddressBookInfo addressBook = findAddressBookById(id);
        AddressBookDTO addressBookResponse = modelMapper.map(addressBook, AddressBookDTO.class);
        return addressBookResponse;
    }

    /**
     * Purpose : Ability to find ID from Address Book database.
     *
     * @param id On providing ID, the user-input is matched with the id value of the database.
     *
     * @return If found, object of AddressBook, else return error message.
     */

    private AddressBookInfo findAddressBookById(int id) {
        log.info("Inside findAddressBookById()");
        return addressBookRepository.findById(id)
                .orElseThrow(() -> new AddressBookException("Unable to find any address book detail!"));
    }

    /**
     * Purpose : Ability to update person details in Address Book based on a particular ID.
     *
     * @param id On providing ID, the user-input is matched with the id value of the database.
     *           If ID is not found, then an error message is returned.
     *
     * @param addressBookDTO If ID is found, Object of AddressBookDTO which will validate user-input
     *                       and once valid, will pass it to the AddressBook entity.
     *                       Finally, the user-input details gets stored in the Database.
     *                       Note : The BeanUtils.copyProperties() has the feature of excluding those attributes
     *                              from the Bean class which we do not wish to update.
     *
     * @return addressBookResponse Object of AddressBookDTO.
     */


    @Override
    public AddressBookDTO updateAddressDetails(int id, AddressBookDTO addressBookDTO) {
        log.info("Inside updateAddressDetails()");
        AddressBookInfo addressBookDetails = findAddressBookById(id);
        String[] ignoreFields = {"id", "name", "createdDate"};
        BeanUtils.copyProperties(addressBookDTO, addressBookDetails, ignoreFields);
        addressBookRepository.save(addressBookDetails);

        return addressBookDTO;
    }

    /**
     * Purpose : Ability to delete person details from Address Book based on a particular ID.
     *
     * @param id On providing ID, the user-input is matched with the id value of the database.
     *           If found, it deletes the person details from Address Book, else returns error message.
     *
     * @return addressBookResponse Object of AddressBookDTO.
     */

    @Override
    public AddressBookDTO deleteAddressDetails(int id) {
        log.info("Inside deleteAddressDetails()");
        AddressBookInfo addressBook = findAddressBookById(id);
        addressBookRepository.delete(addressBook);

        return null;
    }

    /**
     * Purpose : Ability to fetch all state details from State Repository.
     *
     * @return List<StateDTO>
     */

    @Override
    public List<StateDTO> getStateDetails() {
        log.info("Inside getStateDetails()");
        return stateRepository.findAll().stream().map(state -> {
            return new StateDTO(state.getId(), state.getStateName(), state.getCity());
        }).collect(Collectors.toList());
    }
}
