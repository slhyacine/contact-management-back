package com.polyscripts.contactManagement.repos;

import com.polyscripts.contactManagement.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {

    Set<Authority> findByIdIn(List<Long> ids);
}
