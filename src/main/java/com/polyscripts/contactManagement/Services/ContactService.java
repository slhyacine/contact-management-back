package com.polyscripts.contactManagement.Services;

import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.repos.ContactEmployeeRepo;
import com.polyscripts.contactManagement.repos.ContactFreelanceRepo;
import com.polyscripts.contactManagement.repos.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService  {

    private final ContactRepo contactRepo;
    private final ContactEmployeeRepo contactEmployeeRepo;
    private final ContactFreelanceRepo contactFreelanceRepo;

    public ContactService(ContactRepo contactRepo,
                          ContactEmployeeRepo contactEmployeeRepo,
                          ContactFreelanceRepo contactFreelanceRepo) {
        this.contactRepo = contactRepo;
        this.contactEmployeeRepo = contactEmployeeRepo;
        this.contactFreelanceRepo = contactFreelanceRepo;
    }

    public Page<Contact> getAllContactsWithPagination(int offset, int pageSize) {
        Page<Contact> contacts = contactRepo.findAll(PageRequest.of(offset, pageSize));
        return contacts;
    }
}
