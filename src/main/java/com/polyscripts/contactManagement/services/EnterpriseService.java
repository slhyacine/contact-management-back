package com.polyscripts.contactManagement.services;

import com.polyscripts.contactManagement.models.Enterprise;
import com.polyscripts.contactManagement.repositories.EnterpriseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnterpriseService {

    private final EnterpriseRepo enterpriseRepo;

    public EnterpriseService(EnterpriseRepo enterpriseRepo) {
        this.enterpriseRepo = enterpriseRepo;
    }

    public Page<Enterprise> getAllEnterprisesWithPagination(int offset, int pageSize) {
        Page<Enterprise> enterprises = enterpriseRepo.findAll(PageRequest.of(offset, pageSize));
        return enterprises;
    }

    public Enterprise insertEnterprise(Enterprise enterprise) {
        return enterpriseRepo.save(enterprise);
    }

    public Optional<Enterprise> findEnterpriseById(Long id) { return enterpriseRepo.findEnterpriseById(id);  }

    public void deleteEnterprise(Enterprise enterprise) { enterpriseRepo.delete(enterprise); }

}
