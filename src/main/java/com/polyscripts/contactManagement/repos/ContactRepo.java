package com.polyscripts.contactManagement.repos;

import com.polyscripts.contactManagement.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepo extends JpaRepository<Contact, Long> {
}
