package com.polyscripts.contactManagement.controllers;

import com.polyscripts.contactManagement.dtos.*;
import com.polyscripts.contactManagement.exception.ResourceNotFoundException;
import com.polyscripts.contactManagement.services.ContactService;
import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.ContactEmployee;
import com.polyscripts.contactManagement.models.ContactFreelance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ListContactDto getAllContacts(
            @RequestParam int offset,
            @RequestParam int pageSize
    ) {
        Page<Contact> result = contactService.getAllContactsWithPagination(offset, pageSize);
        List<ContactListDto> contacts = result.getContent().stream()
                .map(contact -> modelMapper.map(contact, ContactListDto.class))
                .collect(Collectors.toList());

        ListContactDto listContactDto = new ListContactDto();
        listContactDto.setTotalElements(result.getTotalElements());
        listContactDto.setContent(contacts);
        return listContactDto;
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
            throw new ResourceNotFoundException("There is no contact with id "+id);
        }
        contactService.deleteContact(contact.get());
        return ResponseEntity.ok(1);
    }

    @GetMapping("/{id}")
    public Contact getContact(@PathVariable Long id) {
        Optional<Contact> contact = contactService.findContactById(id);
        if (!contact.isPresent()) {
            throw new ResourceNotFoundException("There is no contact with id "+id);
        }
        return contact.get();
    }

    @PutMapping("/{id}/editEmployee")
    public ContactEmployee updateContactEmployee(
            @PathVariable Long id,
            @RequestBody ContactEmployeeCreateDto contactEmployeeCreateDto) {
        Optional<ContactEmployee> employee = contactService.findEmployeeById(id);
        if (!employee.isPresent()) {
            throw new ResourceNotFoundException("There is no contact with id "+id);
        }
        employee.get().setName(contactEmployeeCreateDto.getName());
        employee.get().setLastname(contactEmployeeCreateDto.getLastname());
        employee.get().setAddress(contactEmployeeCreateDto.getAddress());
        return contactService.insertContactEmployee(employee.get());
    }

    @PutMapping("/{id}/editFreelance")
    public ContactFreelance updateContactFreelance(
            @PathVariable Long id,
            @RequestBody ContactFreelanceCreateDto contactFreelanceCreateDto) {
        Optional<ContactFreelance> employee = contactService.findFreelanceById(id);
        if (!employee.isPresent()) {
            throw new ResourceNotFoundException("There is no contact with id "+id);
        }
        employee.get().setName(contactFreelanceCreateDto.getName());
        employee.get().setLastname(contactFreelanceCreateDto.getLastname());
        employee.get().setAddress(contactFreelanceCreateDto.getAddress());
        employee.get().setTva(contactFreelanceCreateDto.getTva());
        return contactService.insertContactFreelance(employee.get());
    }

}
