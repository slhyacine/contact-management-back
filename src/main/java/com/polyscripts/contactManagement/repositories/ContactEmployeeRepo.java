package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.ContactEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactEmployeeRepo extends JpaRepository<ContactEmployee, Long> {

    Optional<ContactEmployee> findContactEmployeeById(Long id);

}
