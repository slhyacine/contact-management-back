package com.polyscripts.contactManagement.repositories;

import com.polyscripts.contactManagement.models.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnterpriseRepo extends JpaRepository<Enterprise, Long> {

    Optional<Enterprise> findEnterpriseById(Long id);

}
