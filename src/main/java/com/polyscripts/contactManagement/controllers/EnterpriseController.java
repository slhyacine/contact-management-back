package com.polyscripts.contactManagement.controllers;

import com.polyscripts.contactManagement.dtos.EnterpriseCreateDto;
import com.polyscripts.contactManagement.services.EnterpriseService;
import com.polyscripts.contactManagement.models.Enterprise;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<Enterprise> getAllContacts(
            @RequestParam int offset,
            @RequestParam int pageSize
    ) {
        Page<Enterprise> enterprises = enterpriseService.getAllEnterprisesWithPagination(offset, pageSize);
        return enterprises;
    }

    @PostMapping("/new")
    public Enterprise insertEnterprise(
            @Valid @RequestBody EnterpriseCreateDto enterpriseCreateDto
            ) {
        Enterprise enterprise = modelMapper.map(enterpriseCreateDto, Enterprise.class);
        return enterpriseService.insertEnterprise(enterprise);
    }

}
