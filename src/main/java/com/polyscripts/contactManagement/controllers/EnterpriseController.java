package com.polyscripts.contactManagement.controllers;

import com.polyscripts.contactManagement.dtos.*;
import com.polyscripts.contactManagement.exception.ResourceNotFoundException;
import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.services.ContactService;
import com.polyscripts.contactManagement.services.EnterpriseService;
import com.polyscripts.contactManagement.models.Enterprise;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ListEnterpriseDto getAllContacts(
            @RequestParam int offset,
            @RequestParam int pageSize
    ) {
        Page<Enterprise> result = enterpriseService.getAllEnterprisesWithPagination(offset, pageSize);
        List<EnterpriseListDto> enterprises = result.getContent().stream()
                .map(enterprise -> modelMapper.map(enterprise, EnterpriseListDto.class))
                .collect(Collectors.toList());

        ListEnterpriseDto listEnterpriseDto = new ListEnterpriseDto();
        listEnterpriseDto.setTotalElements(result.getTotalElements());
        listEnterpriseDto.setContent(enterprises);
        return listEnterpriseDto;
    }

    @PostMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EnterpriseGetDto insertEnterprise(
            @Valid @RequestBody EnterpriseCreateDto enterpriseCreateDto
            ) {
        Enterprise enterprise = modelMapper.map(enterpriseCreateDto, Enterprise.class);
        Enterprise savedEnterprise = enterpriseService.insertEnterprise(enterprise);
        EnterpriseGetDto enterpriseGetDto = modelMapper.map(savedEnterprise, EnterpriseGetDto.class);
        return enterpriseGetDto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public EnterpriseGetDto updateEnterprise(
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
        Enterprise savedEnterprise = enterpriseService.insertEnterprise(enterprise.get());
        EnterpriseGetDto enterpriseGetDto = modelMapper.map(savedEnterprise, EnterpriseGetDto.class);
        return enterpriseGetDto;
    }

    @DeleteMapping("/{enterpriseId}/contacts/{contacId}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EnterpriseGetDto deleteContactFromEnterprise(
            @PathVariable Long enterpriseId,
            @PathVariable Long contacId
    ) {
        Optional<Enterprise> enterprise = enterpriseService.findEnterpriseById(enterpriseId);
        if (!enterprise.isPresent()) {
            throw new ResourceNotFoundException("There is no enterprise with id : "+enterpriseId);
        }
        Optional<Contact> contact = contactService.findContactById(contacId);
        if (!contact.isPresent()) {
            throw new ResourceNotFoundException("There is no contact with id : " + contacId);
        }
        enterprise.get().removeContact(contact.get());
        Enterprise savedEnterprise = enterpriseService.insertEnterprise(enterprise.get());
        EnterpriseGetDto enterpriseGetDto = modelMapper.map(savedEnterprise, EnterpriseGetDto.class);
        return enterpriseGetDto;
    }

    @PostMapping("/{enterpriseId}/addContact")
    @PreAuthorize("hasAuthority('ADMIN')")
    public EnterpriseGetDto addContactToEnterprise(
            @PathVariable Long enterpriseId,
            @RequestBody AddContactToEnterpriseDto addContactToEnterpriseDto
    ) {
        Optional<Enterprise> enterprise = enterpriseService.findEnterpriseById(enterpriseId);
        if (!enterprise.isPresent()) {
            throw new ResourceNotFoundException("There is no enterprise with id : "+enterpriseId);
        }
        Optional<Contact> contact = contactService.findContactById(addContactToEnterpriseDto.getContactId());
        if (!contact.isPresent()) {
            throw new ResourceNotFoundException("There is no contact with id : " + addContactToEnterpriseDto.getContactId());
        }
        enterprise.get().addContact(contact.get());
        Enterprise savedEnterprise = enterpriseService.insertEnterprise(enterprise.get());
        EnterpriseGetDto enterpriseGetDto = modelMapper.map(savedEnterprise, EnterpriseGetDto.class);
        return enterpriseGetDto;
    }

}
