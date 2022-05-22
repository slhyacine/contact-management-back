package com.polyscripts.contactManagement.services;

import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.ContactEmployee;
import com.polyscripts.contactManagement.models.ContactFreelance;
import com.polyscripts.contactManagement.repositories.ContactEmployeeRepo;
import com.polyscripts.contactManagement.repositories.ContactFreelanceRepo;
import com.polyscripts.contactManagement.repositories.ContactRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ContactService  {

    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ContactEmployeeRepo contactEmployeeRepo;
    @Autowired
    private ContactFreelanceRepo contactFreelanceRepo;

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

    public Optional<ContactEmployee> findEmployeeById(Long id) { return contactEmployeeRepo.findContactEmployeeById(id); }

    public Optional<ContactFreelance> findFreelanceById(Long id) { return contactFreelanceRepo.findContactFreelanceById(id); }

    public Page<Contact> filterContactsByNameAndLastname(String term) {
        return this.contactRepo.findTop3ByNameOrLastName(term, PageRequest.of(0, 5));
    }
}
