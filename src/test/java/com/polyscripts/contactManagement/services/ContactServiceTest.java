package com.polyscripts.contactManagement.services;

import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.ContactEmployee;
import com.polyscripts.contactManagement.models.ContactFreelance;
import com.polyscripts.contactManagement.repositories.ContactEmployeeRepo;
import com.polyscripts.contactManagement.repositories.ContactFreelanceRepo;
import com.polyscripts.contactManagement.repositories.ContactRepo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class ContactServiceTest {

    private ContactRepo contactRepo;
    private ContactEmployeeRepo contactEmployeeRepo;
    private ContactFreelanceRepo contactFreelanceRepo;
    private ContactService contactService;

    @BeforeEach
    void setupTest() {
        contactRepo = mock(ContactRepo.class);
        contactEmployeeRepo = mock(ContactEmployeeRepo.class);
        contactFreelanceRepo = mock(ContactFreelanceRepo.class);
        contactService = new ContactService(contactRepo, contactEmployeeRepo, contactFreelanceRepo);
    }

    @Test
    void insertContactEmployee() {
        ContactEmployee contactEmployee = new ContactEmployee();
        when(contactService.insertContactEmployee(contactEmployee)).thenReturn(contactEmployee);

        ContactEmployee contactEmployee1 = contactService.insertContactEmployee(contactEmployee);

        assertNotNull(contactEmployee1);
    }

    @Test
    void insertContactFreelance() {
        ContactFreelance contactFreelance = new ContactFreelance(1L, "yacine", "souilah", "Baraki", "FREELANCE", null, 11111L);
        when(contactService.insertContactFreelance(contactFreelance)).thenReturn(contactFreelance);

        ContactFreelance contactFreelance1 = contactService.insertContactFreelance(contactFreelance);

        assertNotNull(contactFreelance1);
        assertEquals(contactFreelance1.getType(), "FREELANCE");
    }

    @Test
    void findContactById() {
        Contact contact = new Contact(1L, "yacine", "souilah", "Baraki", null, null);
        when(contactService.findContactById(1L)).thenReturn(Optional.of(contact));

        Optional<Contact> contact1 = contactService.findContactById(1L);

        assertNotNull(contact1);
        assertEquals(1L, contact1.get().getId());
    }

    @Test
    void deleteContact() {}

    @Test
    void findEmployeeById() {
        ContactEmployee contactEmployee = new ContactEmployee(1L, "yacine", "souilah", "Baraki", "EMPLOYEE", null);
        when(contactService.findEmployeeById(1L)).thenReturn(Optional.of(contactEmployee));

        Optional<ContactEmployee> contactEmployee1  = contactService.findEmployeeById(1L);

        assertNotNull(contactEmployee1);
        assertEquals(1L, contactEmployee1.get().getId());
        assertEquals(contactEmployee1.get().getType(), "EMPLOYEE");
    }

    @Test
    void findFreelanceById() {
        ContactFreelance contactFreelance = new ContactFreelance(1L, "yacine", "souilah", "Baraki", "FREELANCE", null, 1111L);
        when(contactService.findFreelanceById(1L)).thenReturn(Optional.of(contactFreelance));

        Optional<ContactFreelance> contactFreelance1 = contactService.findFreelanceById(1L);

        assertNotNull(contactFreelance1);
        assertEquals(1L, contactFreelance1.get().getId());
        assertEquals(contactFreelance1.get().getType(), "FREELANCE");
    }
}