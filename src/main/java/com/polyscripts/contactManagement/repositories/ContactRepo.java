package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    Optional<Contact> findOneById(Long id);
}
