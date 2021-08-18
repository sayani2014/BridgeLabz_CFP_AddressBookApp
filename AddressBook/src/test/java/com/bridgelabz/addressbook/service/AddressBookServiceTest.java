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
     */

    @Test
    public void getAddressDetailsTest() {
        List<AddressBookInfo> addresses = new ArrayList<>();

        AddressBookInfo addressBookInfo1 = new AddressBookInfo();
        addressBookInfo1.setId(1);
        addressBookInfo1.setName("Sayani");
        addressBookInfo1.setAddress("WB");
        addressBookInfo1.setPhoneNo("123456");
        addressBookInfo1.setEmail("abc@abc.in");
        addresses.add(addressBookInfo1);

        AddressBookInfo addressBookInfo2 = new AddressBookInfo();
        addressBookInfo2.setId(2);
        addressBookInfo2.setName("Suvadeep");
        addressBookInfo2.setAddress("WB");
        addressBookInfo2.setPhoneNo("345656");
        addressBookInfo2.setEmail("abc1@abc.in");
        addresses.add(addressBookInfo2);

        AddressBookDTO addressBookDTO1 = new AddressBookDTO();
        addressBookDTO1.setId(1);
        addressBookDTO1.setName("Sayani");
        addressBookDTO1.setAddress("WB");
        addressBookDTO1.setPhoneNo("123456");
        addressBookDTO1.setEmail("abc@abc.in");

        AddressBookDTO addressBookDTO2 = new AddressBookDTO();
        addressBookDTO2.setId(2);
        addressBookDTO2.setName("Suvadeep");
        addressBookDTO2.setAddress("WB");
        addressBookDTO2.setPhoneNo("345656");
        addressBookDTO2.setEmail("abc1@abc.in");

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
        assertEquals("Suvadeep", actualAddressBook.get(1).getName());
    }

    /**
     * Purpose : Ability to test the addAddressDetails().
     */

    @Test
    public void addAddressDetailsTest() {
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("WB");
        addressBookDTO.setPhoneNo("123456");
        addressBookDTO.setEmail("abc@abc.in");

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("WB");
        addressBookInfo.setPhoneNo("123456");
        addressBookInfo.setEmail("abc@abc.in");

        when(addressBuilder.buildDO(addressBookDTO)).thenReturn(addressBookInfo);
        addressBookInfo.setId(1);
        when(addressBookRepo.save(addressBookInfo)).thenReturn(addressBookInfo);

        AddressBookDTO addressBookDTO1 = addressBookService.addAddressDetails(addressBookDTO);
        assertNotNull(addressBookDTO1);
        assertEquals(1, addressBookDTO1.getId());
    }

    /**
     * Purpose : Ability to test the updateAddressDetails() when ID is not found.
     */

    @Test(expected = AddressBookException.class)
    public void updateAddressDetails_whenFindById_shouldThrowExceptionTest() {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("WB");
        addressBookDTO.setPhoneNo("123456");
        addressBookDTO.setEmail("abc@abc.in");

        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        addressBookService.updateAddressDetails(id, addressBookDTO);
    }

    /**
     * Purpose : Ability to test the updateAddressDetails() when ID is found.
     */

    @Test
    public void updateAddressDetailsTest() {
        int id = 1;
        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("MH");
        addressBookDTO.setPhoneNo("123456");
        addressBookDTO.setEmail("abc@abc.in");

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setId(1);
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("WB");
        addressBookInfo.setPhoneNo("258963");
        addressBookInfo.setEmail("abc1@abc.in");

        when(addressBookRepo.findById(id)).thenReturn(Optional.of(addressBookInfo));
        when(addressBookRepo.save(addressBookInfo)).thenReturn(addressBookInfo);
        AddressBookDTO actualAddressBookDTO = addressBookService.updateAddressDetails(id, addressBookDTO);
        ArgumentCaptor<AddressBookInfo> addressBookDOArgumentCaptor = ArgumentCaptor.forClass(AddressBookInfo.class);
        verify(addressBookRepo, times(1)).save(addressBookDOArgumentCaptor.capture());
        assertNotNull(actualAddressBookDTO);
        assertEquals("Sayani", addressBookDOArgumentCaptor.getValue().getName());
        assertEquals("MH", addressBookDOArgumentCaptor.getValue().getAddress());

    }

    /**
     * Purpose : Ability to test the deleteAddressDetails() when ID is not found.
     */

    @Test(expected = AddressBookException.class)
    public void deleteAddressDetails_whenFindById_shouldThrowExceptionTest() {
        int id = 1;

        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        addressBookService.deleteAddressDetails(id);
    }

    /**
     * Purpose : Ability to test the deleteAddressDetails() when ID is found.
     */

    @Test
    public void deleteAddressDetailsTest() {
        int id = 1;

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setId(1);
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("WB");
        addressBookInfo.setPhoneNo("258963");
        addressBookInfo.setEmail("abc1@abc.in");

        when(addressBookRepo.findById(id)).thenReturn(Optional.of(addressBookInfo));
        when(addressBookRepo.save(addressBookInfo)).thenReturn(addressBookInfo);
        AddressBookDTO actualAddressBookDTO = addressBookService.deleteAddressDetails(id);
        ArgumentCaptor<AddressBookInfo> addressBookDOArgumentCaptor = ArgumentCaptor.forClass(AddressBookInfo.class);
        verify(addressBookRepo, times(1)).delete(addressBookDOArgumentCaptor.capture());
        assertNull(actualAddressBookDTO);
    }

    /**
     * Purpose : Ability to test the getAddressDetailsByID() when ID is not found.
     */

    @Test(expected = AddressBookException.class)
    public void getAddressDetailsByID_whenFindById_shouldThrowExceptionTest() {
        int id = 1;

        when(addressBookRepo.findById(id)).thenReturn(Optional.empty());
        addressBookService.getAddressDetailsByID(id);
    }

    /**
     * Purpose : Ability to test the getAddressDetailsByID() when ID is found.
     */

    @Test
    public void getAddressDetailsByIDTest() {
        int id = 1;

        AddressBookInfo addressBookInfo = new AddressBookInfo();
        addressBookInfo.setId(1);
        addressBookInfo.setName("Sayani");
        addressBookInfo.setAddress("WB");
        addressBookInfo.setPhoneNo("123456");
        addressBookInfo.setEmail("abc@abc.in");

        AddressBookDTO addressBookDTO = new AddressBookDTO();
        addressBookDTO.setId(1);
        addressBookDTO.setName("Sayani");
        addressBookDTO.setAddress("WB");
        addressBookDTO.setPhoneNo("123456");
        addressBookDTO.setEmail("abc@abc.in");

        when(addressBookRepo.findById(id)).thenReturn(Optional.of(addressBookInfo));
        when(modelMapper.map(addressBookInfo, AddressBookDTO.class)).thenReturn(addressBookDTO);

        AddressBookDTO actualAddressBook = addressBookService.getAddressDetailsByID(id);

        assertNotNull(actualAddressBook);
        assertEquals(1, actualAddressBook.getId());

        assertEquals("Sayani", actualAddressBook.getName());
    }
}
