package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.AddressBookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookInfo, Integer> {

    @Query(value = "select name from address_book_info", nativeQuery = true)
    List<String> findByName();
}
