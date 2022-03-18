package com.polyscripts.contactManagement.Services;

import com.polyscripts.contactManagement.models.Enterprise;
import com.polyscripts.contactManagement.repos.EnterpriseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

}
