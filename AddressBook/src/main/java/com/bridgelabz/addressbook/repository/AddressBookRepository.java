package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.AddressBookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressBookRepository extends JpaRepository<AddressBookInfo, Integer> {
}
