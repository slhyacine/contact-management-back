package com.polyscripts.contactManagement.Controllers;

import com.polyscripts.contactManagement.DTOs.ContactEmployeeCreateDto;
import com.polyscripts.contactManagement.DTOs.ContactFreelanceCreateDto;
import com.polyscripts.contactManagement.Services.ContactService;
import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.ContactEmployee;
import com.polyscripts.contactManagement.models.ContactFreelance;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody ContactEmployeeCreateDto contactEmployeeCreateDto
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

}
