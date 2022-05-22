package com.polyscripts.contactManagement.services;

import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.Enterprise;
import com.polyscripts.contactManagement.repositories.EnterpriseRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseService {

    @Autowired
    private EnterpriseRepo enterpriseRepo;

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
