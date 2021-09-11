package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

//    @Query(value = "select state_name from state", nativeQuery = true)
//    List<String> getState();
}
