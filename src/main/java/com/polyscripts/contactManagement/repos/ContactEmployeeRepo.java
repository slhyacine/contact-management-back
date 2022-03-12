package com.polyscripts.contactManagement.repos;

import com.polyscripts.contactManagement.models.ContactEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactEmployeeRepo extends JpaRepository<ContactEmployee, Long> {
}
