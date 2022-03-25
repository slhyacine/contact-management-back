package com.polyscripts.contactManagement.controllers;

import com.polyscripts.contactManagement.dtos.ContactEmployeeCreateDto;
import com.polyscripts.contactManagement.dtos.ContactFreelanceCreateDto;
import com.polyscripts.contactManagement.exception.ContactNotFoundException;
import com.polyscripts.contactManagement.services.ContactService;
import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.ContactEmployee;
import com.polyscripts.contactManagement.models.ContactFreelance;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public Page<Contact> getAllContacts(
            @RequestParam int offset,
            @RequestParam int pageSize
    ) {
        Page<Contact> contacts = contactService.getAllContactsWithPagination(offset, pageSize);
        return contacts;
    }

    @PostMapping("/newEmployee")
    public ContactEmployee insertContactEmployee(
            @Valid @RequestBody ContactEmployeeCreateDto contactEmployeeCreateDto
            ) {
        ContactEmployee contactEmployee = modelMapper.map(contactEmployeeCreateDto, ContactEmployee.class);
        return contactService.insertContactEmployee(contactEmployee);
    }

    @PostMapping("/newFreelance")
    public ContactFreelance insertContactEmployee(
            @RequestBody ContactFreelanceCreateDto contactFreelanceCreateDto
    ) {
        ContactFreelance contactFreelance = modelMapper.map(contactFreelanceCreateDto, ContactFreelance.class);
        return contactService.insertContactFreelance(contactFreelance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Long id) {
        Optional<Contact> contact = contactService.findContactById(id);
        if (!contact.isPresent()) {
            throw new ContactNotFoundException("There is no contact with id "+id);
        }
        contactService.deleteContact(contact.get());
        return ResponseEntity.ok(1);
    }

}
