package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.ContactFreelance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFreelanceRepo extends JpaRepository<ContactFreelance, Long> {
}
