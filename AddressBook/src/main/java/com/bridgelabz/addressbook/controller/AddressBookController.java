package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.dto.ResponseDTO;
import com.bridgelabz.addressbook.service.IAddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class AddressBookController {

    @Autowired
    private IAddressBookService addressBookService;

    /**
     * Purpose : Ability to insert person details in Address Book.
     *
     * @param addressBookDTO Object of AddressBookDTO which will validate user-input
     *                       and once valid, will pass it to the AddressBook entity.
     *                       Finally, the user-input details gets stored in the Database.
     *
     * @return responseDTO Object of ResponseDTO which returns the status of the POST Method.
     */

    @PostMapping(value = "/addAddressDetails")
    public ResponseEntity<ResponseDTO> addAddressDetails(@Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Inside addAddressDetails()");
        AddressBookDTO addData = addressBookService.addAddressDetails(addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Added Address Book Details", addData);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * Purpose : Ability to fetch all person details from Address Book.
     *
     * @return responseDTO Object of ResponseDTO which returns the status of the GET Method.
     */

    @GetMapping(value = "/getAddressDetails")
    public ResponseEntity<ResponseDTO> getAddressDetails() {
        log.info("Inside getAddressDetails()");
        List<AddressBookDTO> addressbookList = addressBookService.getAddressDetails();
        ResponseDTO responseDTO = new ResponseDTO("Fetched all Address Book Details", addressbookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose : Ability to fetch person details from Address Book based on a particular ID.
     *
     * @param id On providing ID, the user-input is matched with the id value of the database.
     *           If found, it returns the person details from Address Book, else returns error message.
     *
     * @return responseDTO Object of ResponseDTO which returns the status of the GET Method.
     */

    @GetMapping(value = "/getAddressDetailsByID")
    public ResponseEntity<ResponseDTO> getAddressDetailsByID(@RequestParam(name = "id") int id) {
        log.info("Inside getAddressDetailsByID()");
        AddressBookDTO addressBookDTO = addressBookService.getAddressDetailsByID(id);
        ResponseDTO responseDTO = new ResponseDTO("Fetched by ID : Address Book Details", addressBookDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
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
     *
     * @return responseDTO Object of ResponseDTO which returns the status of the PUT Method.
     */

    @PutMapping(value = "/updateAddressDetails")
    public ResponseEntity<ResponseDTO> updateAddressDetails(@RequestParam(name = "id") int id,
                                                    @Valid @RequestBody AddressBookDTO addressBookDTO) {
        log.info("Inside updateAddressDetails()");
        AddressBookDTO updatedData = addressBookService.updateAddressDetails(id, addressBookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated by ID : Address Book Details", updatedData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * Purpose : Ability to delete person details from Address Book based on a particular ID.
     *
     * @param id On providing ID, the user-input is matched with the id value of the database.
     *           If found, it deletes the person details from Address Book, else returns error message.
     *
     * @return responseDTO Object of ResponseDTO which returns the status of the DELETE Method.
     */

    @DeleteMapping(value = "/deleteAddressDetails")
    public ResponseEntity<ResponseDTO> deleteAddressDetails(@RequestParam(name = "id") int id) {
        log.info("Inside deleteAddressDetails()");
        AddressBookDTO deletedData = addressBookService.deleteAddressDetails(id);
        ResponseDTO responseDTO = new ResponseDTO("Deleted by ID : Address Book Details", deletedData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
