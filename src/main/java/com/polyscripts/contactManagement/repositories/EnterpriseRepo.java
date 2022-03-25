package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnterpriseRepo extends JpaRepository<Enterprise, Long> {
}
