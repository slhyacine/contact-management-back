package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    Optional<Contact> findOneById(Long id);
}
