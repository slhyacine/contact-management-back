package com.polyscripts.contactManagement.controllers;

import com.polyscripts.contactManagement.dtos.EnterpriseCreateDto;
import com.polyscripts.contactManagement.dtos.EnterpriseGetDto;
import com.polyscripts.contactManagement.exception.ResourceNotFoundException;
import com.polyscripts.contactManagement.services.EnterpriseService;
import com.polyscripts.contactManagement.models.Enterprise;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.Optional;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnterprise(@PathVariable Long id) {
        Optional<Enterprise> enterprise = enterpriseService.findEnterpriseById(id);
        if (!enterprise.isPresent()) {
            throw new ResourceNotFoundException("There is no enterprise with id "+id);
        }
        enterpriseService.deleteEnterprise(enterprise.get());
        return ResponseEntity.ok(1);
    }

    @GetMapping("/{id}")
    public EnterpriseGetDto getEnterprise(@PathVariable Long id) {
        Optional<Enterprise> enterprise = enterpriseService.findEnterpriseById(id);
        if (!enterprise.isPresent()) {
            throw new ResourceNotFoundException("There is no enterprise with id : "+id);
        }
        EnterpriseGetDto enterpriseGetDto = modelMapper.map(enterprise.get(), EnterpriseGetDto.class);
        return enterpriseGetDto;
    }

    @PutMapping("/{id}")
    public Enterprise updateEnterprise(
            @PathVariable Long id,
            @RequestBody EnterpriseCreateDto enterpriseCreateDto
    ) {
        Optional<Enterprise> enterprise = enterpriseService.findEnterpriseById(id);
        if (!enterprise.isPresent()) {
            throw new ResourceNotFoundException("There is no enterprise with id : "+id);
        }
        enterprise.get().setName(enterpriseCreateDto.getName());
        enterprise.get().setAddress(enterpriseCreateDto.getAddress());
        enterprise.get().setTva(enterpriseCreateDto.getTva());
        return enterpriseService.insertEnterprise(enterprise.get());
    }

}
