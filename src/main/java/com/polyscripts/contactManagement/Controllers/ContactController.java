package com.polyscripts.contactManagement.Controllers;

import com.polyscripts.contactManagement.Services.ContactService;
import com.polyscripts.contactManagement.models.Contact;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Contact>> getAllContacts(
            @RequestParam int offset,
            @RequestParam int pageSize
    ) {
        Page<Contact> contacts = contactService.getAllContactsWithPagination(offset, pageSize);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

}
