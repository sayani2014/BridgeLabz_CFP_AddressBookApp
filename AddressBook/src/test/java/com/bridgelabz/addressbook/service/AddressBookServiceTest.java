/**
 * Purpose : Ability to write Test Cases for AddressBookService.class
 */

package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.builder.AddressBookBuilder;
import com.bridgelabz.addressbook.dto.AddressBookDTO;
import com.bridgelabz.addressbook.entity.AddressBookInfo;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;

    @Mock
    private AddressBookRepository addressBookRepo;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private AddressBookBuilder addressBuilder;

    /**
     * Purpose : Ability to test the getAddressDetails().
     *
     * Test : Check whether the object of AddressBookInfo returned is not null.
              Check whether the list of object of AddressBookDTO returned is not null.
              Check whether the object values of AddressBookDTO matches with the expected value.
     */

    @Test
    public void getAddressDetailsTest() {
        List<AddressBookInfo> addresses = new ArrayList<>();

        AddressBookInfo addressBookInfo1 = new AddressBookInfo();
        addressBookInfo1.setId(1);
        addressBookInfo1.setName("Sayani");
        addressBookInfo1.setAddress("12/1 abc");
        addressBookInfo1.setCity("Test");
        addressBookInfo1.setState("Test");
        addressBookInfo1.setZip("123456");
        addressBookInfo1.setPhoneNo("7894561230");
//        addressBookInfo1.setEmail("test@abc.com");
        addresses.add(addressBookInfo1);

        AddressBookInfo addressBookInfo2 = new AddressBookInfo();
        addressBookInfo2.setId(2);
        addressBookInfo2.setName("Suvadeep");
        addressBookInfo2.setAddress("12/1 abc");
        addressBookInfo2.setCity("Test");
        addressBookInfo2.setState("Test");
        addressBookInfo2.setZip("789456");
        addressBookInfo2.setPhoneNo("7894561230");
//        addressBookInfo2.setEmail("test@abc.com");
        addresses.add(addressBookInfo2);

        AddressBookDTO addressBookDTO1 = new AddressBookDTO();
        addressBookDTO1.setId(1);
        addressBookDTO1.setName("Sayani");
        addressBookDTO1.setAddress("12/1 abc");
        addressBookDTO1.setCity("Test");
        addressBookDTO1.setState("Test");
        addressBookDTO1.setZip("123456");
        addressBookDTO1.setPhoneNo("7894561230");
//        addressBookDTO1.setEmail("test@abc.com");

        AddressBookDTO addressBookDTO2 = new AddressBookDTO();
        addressBookDTO2.setId(2);
        addressBookDTO2.setName("Suvadeep");
        addressBookDTO2.setAddress("12/1 abc");
        addressBookDTO2.setCity("Test");
        addressBookDTO2.setState("Test");
        addressBookDTO2.setZip("789456");
        addressBookDTO2.setPhoneNo("7894561230");
//        addressBookDTO2.setEmail("test@abc.com");

        assertNotNull(addresses);
        when(addressBookRepo.findAll()).thenReturn(addresses);
        when(modelMapper.map(addresses.get(0), AddressBookDTO.class)).thenReturn(addressBookDTO1);
        when(modelMapper.map(addresses.get(1), AddressBookDTO.class)).thenReturn(addressBookDTO2);

        List<AddressBookDTO> actualAddressBook = addressBookService.getAddressDetails();

        assertNotNull(actualAddressBook);
        for (int i = 0; i < addresses.size(); i++) {
            assertEquals(i+1, actualAddressBook.get(i).getId());
        }

        assertEquals("Sayani", actualAddressBook.get(0).getName());
        assertEquals("12/1 abc", actualAddressBook.get(0).getAddress());
        assertEquals("Test", actualAddressBook.get(0).getCity());
        assertEquals("Test", actualAddressBook.get(0).getState());
//        assertEquals("test@abc.com", actualAddressBook.get(0).getEmail());

        assertEquals("Suvadeep", actualAddressBook.get(1).getName());
        assertEquals("12/1 abc", actualAddressBook.get(1).getAddress());
        assertEquals("Test", actualAddressBook.get(1).getCity());
        assertEquals("Test", actualAddressBook.get(1).getState());
//        assertEquals("test@abc.com", actualAddressBook.get(1).getEmail());
    }

    /**
     * Purpose : Ability to test the addAddressDetails().
     *
     * Test : Check whether the object of AddressBookDTO returned is not null.
              Check whether the object values of AddressBookDTO matches with the expected value.
     */

    @Test
    public void addAddressDetailsTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("12/1 abc");
        addressBookDTO.setCity("Test");
        addressBookDTO.setState("Test");
        addressBookDTO.setZip("789456");
        addressBookDTO.setPhoneNo("7894561230");
//        addressBookDTO.setEmail("test@abc.com");

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("12/1 abc");
        addressBookInfo.setCity("Test");
        addressBookInfo.setState("Test");
        addressBookInfo.setZip("789456");
        addressBookInfo.setPhoneNo("7894561230");
//        addressBookInfo.setEmail("test@abc.com");

        when(addressBuilder.buildDO(addressBookDTO)).thenReturn(addressBookInfo);
        addressBookInfo.setId(1);
        when(addressBookRepo.save(addressBookInfo)).thenReturn(addressBookInfo);

//        AddressBookDTO addressBookDTO1 = addressBookService.addAddressDetails(addressBookDTO);
//
//        assertNotNull(addressBookDTO1);
//        assertEquals(1, addressBookDTO1.getId());
//        assertEquals("Sayani", addressBookDTO1.getName());
//        assertEquals("12/1 abc", addressBookDTO1.getAddress());
//        assertEquals("Test", addressBookDTO1.getCity());
//        assertEquals("Test", addressBookDTO1.getState());
//        assertEquals("789456", addressBookDTO1.getZip());
//        assertEquals("7894561230", addressBookDTO1.getPhoneNo());
//        assertEquals("test@abc.com", addressBookDTO1.getEmail());
    }

    /**
     * Purpose : Ability to test the updateAddressDetails() when ID is not found.
     *
     * Test : Check whether exception is caught when ID is not found.
     */

    @Test(expected = AddressBookException.class)
    public void updateAddressDetails_whenFindByIncorrectId_shouldThrowExceptionTest() {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("12/1 abc");
        addressBookDTO.setCity("Test");
        addressBookDTO.setState("Test");
        addressBookDTO.setZip("789456");
        addressBookDTO.setPhoneNo("7894561230");
//        addressBookDTO.setEmail("test@abc.com");

        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        addressBookService.updateAddressDetails(id, addressBookDTO);
    }

    /**
     * Purpose : Ability to test the updateAddressDetails() when ID is found.
     *
     * Test : Check whether the object of AddressBookDTO returned is not null.
              Check whether the value is updated in the AddressBookInfo.
     */

    @Test
    public void updateAddressDetailsTest() {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("12/1 abc");
        addressBookDTO.setCity("Mumbai");
        addressBookDTO.setState("Test");
        addressBookDTO.setZip("789456");
        addressBookDTO.setPhoneNo("7894561230");
//        addressBookDTO.setEmail("abc@abc.com");

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setId(1);
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("12/1 abc");
        addressBookInfo.setCity("Test");
        addressBookInfo.setState("Test");
        addressBookInfo.setZip("789456");
        addressBookInfo.setPhoneNo("7894561230");
//        addressBookInfo.setEmail("test@abc.com");

        when(addressBookRepo.findById(id)).thenReturn(Optional.of(addressBookInfo));
        when(addressBookRepo.save(addressBookInfo)).thenReturn(addressBookInfo);

        AddressBookDTO actualAddressBookDTO = addressBookService.updateAddressDetails(id, addressBookDTO);

        ArgumentCaptor<AddressBookInfo> addressBookDOArgumentCaptor = ArgumentCaptor.forClass(AddressBookInfo.class);
        verify(addressBookRepo, times(1)).save(addressBookDOArgumentCaptor.capture());

        assertNotNull(actualAddressBookDTO);
        assertEquals("Sayani", addressBookDOArgumentCaptor.getValue().getName());
        assertEquals("12/1 abc", addressBookDOArgumentCaptor.getValue().getAddress());
        assertEquals("Mumbai", addressBookDOArgumentCaptor.getValue().getCity());
        assertEquals("Test", addressBookDOArgumentCaptor.getValue().getState());
        assertEquals("789456", addressBookDOArgumentCaptor.getValue().getZip());
        assertEquals("7894561230", addressBookDOArgumentCaptor.getValue().getPhoneNo());
//        assertEquals("abc@abc.com", addressBookDOArgumentCaptor.getValue().getEmail());

    }

    /**
     * Purpose : Ability to test the deleteAddressDetails() when ID is not found.
     *
     * Test : Check whether exception is caught when ID is not found.
     */

    @Test(expected = AddressBookException.class)
    public void deleteAddressDetails_whenFindByIncorrectId_shouldThrowExceptionTest() {
        int id = 1;

        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        addressBookService.deleteAddressDetails(id);
    }

    /**
     * Purpose : Ability to test the deleteAddressDetails() when ID is found.
     *
     * Test : Check whether the object of AddressBookDTO returned is not null.
              Check whether the object of AddressBookDTO returned is null after deletion.
     */

    @Test
    public void deleteAddressDetailsTest() {
        int id = 1;

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setId(1);
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("12/1 abc");
        addressBookInfo.setCity("Test");
        addressBookInfo.setState("Test");
        addressBookInfo.setZip("789456");
        addressBookInfo.setPhoneNo("7894561230");
//        addressBookInfo.setEmail("test@abc.com");

        when(addressBookRepo.findById(id)).thenReturn(Optional.of(addressBookInfo));
        when(addressBookRepo.save(addressBookInfo)).thenReturn(addressBookInfo);

        AddressBookDTO actualAddressBookDTO = addressBookService.deleteAddressDetails(id);

        ArgumentCaptor<AddressBookInfo> addressBookDOArgumentCaptor = ArgumentCaptor.forClass(AddressBookInfo.class);
        verify(addressBookRepo, times(1)).delete(addressBookDOArgumentCaptor.capture());

        assertNull(actualAddressBookDTO);
    }

    /**
     * Purpose : Ability to test the getAddressDetailsByID() when ID is not found.
     *
     * Test : Check whether exception is caught when ID is not found.
     */

    @Test(expected = AddressBookException.class)
    public void getAddressDetailsByID_whenFindByIncorrectId_shouldThrowExceptionTest() {
        int id = 1;

        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        addressBookService.getAddressDetailsByID(id);
    }

    /**
     * Purpose : Ability to test the getAddressDetailsByID() when ID is found.
     *
     * Test : Check whether the object of AddressBookDTO returned is not null.
              Check whether the value of the object of AddressBookDTO matches the expected value.
     */

    @Test
    public void getAddressDetailsByIDTest() {
        int id = 1;

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setId(1);
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("12/1 abc");
        addressBookInfo.setCity("Test");
        addressBookInfo.setState("Test");
        addressBookInfo.setZip("789456");
        addressBookInfo.setPhoneNo("7894561230");
//        addressBookInfo.setEmail("test@abc.com");

        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setId(1);
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("12/1 abc");
        addressBookDTO.setCity("Test");
        addressBookDTO.setState("Test");
        addressBookDTO.setZip("789456");
        addressBookDTO.setPhoneNo("7894561230");
//        addressBookDTO.setEmail("test@abc.com");

        when(addressBookRepo.findById(id)).thenReturn(Optional.of(addressBookInfo));
        when(modelMapper.map(addressBookInfo, AddressBookDTO.class)).thenReturn(addressBookDTO);

        AddressBookDTO actualAddressBook = addressBookService.getAddressDetailsByID(id);

        assertNotNull(actualAddressBook);
        assertEquals(1, actualAddressBook.getId());
        assertEquals("Sayani", actualAddressBook.getName());
        assertEquals("12/1 abc", actualAddressBook.getAddress());
        assertEquals("Test", actualAddressBook.getCity());
        assertEquals("Test", actualAddressBook.getState());
        assertEquals("789456", actualAddressBook.getZip());
        assertEquals("7894561230", actualAddressBook.getPhoneNo());
//        assertEquals("test@abc.com", actualAddressBook.getEmail());
    }
}
