package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.AddressBookApplication;
import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.service.AddressBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/addressbook")
public class AddressBookController {
    public static final Logger logger = LoggerFactory.getLogger(AddressBookController.class);

    @Autowired
    private AddressBookService addressBookService;

    @PostMapping(value = "/addAddressDetails")
    public ResponseEntity<AddressBookDTO> addAddressDetails(@RequestBody AddressBookDTO addressBookDTO) {
        logger.debug("Inside addAddressDetails()");
        return new ResponseEntity<>(addressBookService.addAddressDetails(addressBookDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/getAddressDetails")
    public ResponseEntity<List<AddressBookDTO>> getAddressDetails() {
        logger.debug("Inside getAddressDetails()");
        return new ResponseEntity<>(addressBookService.getAddressDetails(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAddressDetailsByID")
    public ResponseEntity<AddressBookDTO> getAddressDetailsByID(@RequestParam(name = "id") int id) {
        logger.debug("Inside getAddressDetailsByID()");
        return new ResponseEntity<>(addressBookService.getAddressDetailsByID(id), HttpStatus.OK);
    }

    @PutMapping(value = "/updateAddressDetails")
    public ResponseEntity<AddressBookDTO> updateAddressDetails(@RequestParam(name = "id") int id,
                                                      @RequestBody AddressBookDTO addressBookDTO) {
        logger.debug("Inside updateAddressDetails()");
        return new ResponseEntity<>(addressBookService.updateAddressDetails(id, addressBookDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteAddressDetails")
    public ResponseEntity<String> deleteAddressDetails(@RequestParam(name = "id") int id) {
        logger.debug("Inside deleteAddressDetails()");
        return new ResponseEntity<>(addressBookService.deleteAddressDetails(id), HttpStatus.OK);
    }
}
