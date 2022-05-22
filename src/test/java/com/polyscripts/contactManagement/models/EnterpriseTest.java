package com.polyscripts.contactManagement.models;

import com.polyscripts.contactManagement.repositories.ContactRepo;
import com.polyscripts.contactManagement.repositories.EnterpriseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class EnterpriseTest {

    private EnterpriseRepo enterpriseRepo;
    private ContactRepo contactRepo;

    @BeforeEach
    void setupTest() {
        enterpriseRepo = mock(EnterpriseRepo.class);
        contactRepo = mock(ContactRepo.class);
    }

    @Test
    @DisplayName("When add a contact to an enterprise, the enterprise contacts count will increase by one")
    void whenAddContactToEnterprise_increaseEnterpriseContactsCount() {
        Enterprise enterprise = new Enterprise(1L, "enterprise1", "Baraki", 111L, new ArrayList<>());
        Contact contact = new Contact(1L, "Yac", "Sou", "Dalboz city", "EMPLOYEE", new ArrayList<>());
        enterprise.addContact(contact);

        assertThat(enterprise.getContacts().size()).isEqualTo(1);
        assertThat(contact.getEnterprises().size()).isEqualTo(1);
        assertEquals(enterprise.getContacts().get(0).getId(), contact.getId());
    }

    @Test
    @DisplayName("When removing contact from enterprise make sur the enteprise contacts count decrease")
    void whenRemovingContactFromEnterprise_decreaseEnterpriseContactsCount() {
        Contact contact = new Contact(1L, "Yac", "Sou", "Dalboz city", "EMPLOYEE", new ArrayList<>());
        Enterprise enterprise = new Enterprise(1L, "enterprise1", "Baraki", 111L, new ArrayList<>());
        enterprise.getContacts().add(contact);
        contact.getEnterprises().add(enterprise);

        // todo make sure the removed contact is the one add before the test
        enterprise.removeContact(contact);

        assertThat(enterprise.getContacts().size()).isEqualTo(0);
        assertThat(contact.getEnterprises().size()).isEqualTo(0);
    }
}