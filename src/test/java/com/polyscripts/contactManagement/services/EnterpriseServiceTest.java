package com.polyscripts.contactManagement.services;

import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.Enterprise;
import com.polyscripts.contactManagement.repositories.EnterpriseRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class EnterpriseServiceTest {

    private EnterpriseRepo enterpriseRepo;
    private EnterpriseService enterpriseService;

    @BeforeEach
    void setupTest() {
        enterpriseRepo = mock(EnterpriseRepo.class);
        enterpriseService = new EnterpriseService(enterpriseRepo);
    }

    @Test
    void insertEnterprise() {
        Enterprise enterprise = new Enterprise();
        when(enterpriseService.insertEnterprise(enterprise)).thenReturn(enterprise);

        Enterprise enterprise1 = enterpriseService.insertEnterprise(enterprise);

        assertNotNull(enterprise1);
    }

    @Test
    void findEnterpriseById() {
        Enterprise enterprise = new Enterprise(1L, "YASSIR", "SAID HAMDINE", 1111L,  null);
        when(enterpriseService.findEnterpriseById(1L)).thenReturn(Optional.of(enterprise));

        Optional<Enterprise> enterprise1 = enterpriseService.findEnterpriseById(1L);

        assertNotNull(enterprise1);
        assertEquals(1L, enterprise1.get().getId());
    }
}