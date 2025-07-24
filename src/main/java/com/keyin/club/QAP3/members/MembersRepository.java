package com.keyin.club.QAP3.members;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MembersRepository extends CrudRepository<Members, Long> {

    List<Members> findByNameContainingIgnoreCase(String name);

    List<Members> findByPhoneNumber(String phoneNumber);

    List<Members> findByMembershipStartDate(LocalDate startDate);

    List<Members> findByEmailContainingIgnoreCase(String email);

    // List<Members> findByMembershipType(String type);
}
