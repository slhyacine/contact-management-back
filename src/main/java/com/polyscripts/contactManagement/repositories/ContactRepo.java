package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    Optional<Contact> findOneById(Long id);

    @Query("select c from Contact c where lower(c.name) like lower(concat('%', :search, '%')) " +
            "or lower(c.lastname) like lower(concat('%', :search, '%'))")
    Page<Contact> findTop3ByNameOrLastName(@Param("search") String search, Pageable pageable);
}
