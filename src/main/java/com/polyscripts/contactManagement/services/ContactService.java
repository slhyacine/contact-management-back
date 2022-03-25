package com.polyscripts.contactManagement.services;

import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.ContactEmployee;
import com.polyscripts.contactManagement.models.ContactFreelance;
import com.polyscripts.contactManagement.repositories.ContactEmployeeRepo;
import com.polyscripts.contactManagement.repositories.ContactFreelanceRepo;
import com.polyscripts.contactManagement.repositories.ContactRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public ContactEmployee insertContactEmployee(ContactEmployee contactEmployee) {
        return contactEmployeeRepo.save(contactEmployee);
    }

    public ContactFreelance insertContactFreelance(ContactFreelance contactFreelance) {
        return contactFreelanceRepo.save(contactFreelance);
    }

    public Optional<Contact> findContactById(Long id) {
        return contactRepo.findOneById(id);
    }

    public void deleteContact(Contact contact) {
        contactRepo.delete(contact);
    }

}
