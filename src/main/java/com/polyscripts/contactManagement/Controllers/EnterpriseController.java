package com.polyscripts.contactManagement.Controllers;

import com.polyscripts.contactManagement.Services.EnterpriseService;
import com.polyscripts.contactManagement.models.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    public final EnterpriseService enterpriseService;

    public EnterpriseController(EnterpriseService enterpriseService) {
        this.enterpriseService = enterpriseService;
    }

    @GetMapping("/list")
    public Page<Enterprise> getAllContacts(
            @RequestParam int offset,
            @RequestParam int pageSize
    ) {
        Page<Enterprise> enterprises = enterpriseService.getAllEnterprisesWithPagination(offset, pageSize);
        return enterprises;
    }

}
