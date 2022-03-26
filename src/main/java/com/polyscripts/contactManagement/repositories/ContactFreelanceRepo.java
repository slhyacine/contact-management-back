package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.ContactFreelance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactFreelanceRepo extends JpaRepository<ContactFreelance, Long> {

    Optional<ContactFreelance> findContactFreelanceById(Long id);

}
