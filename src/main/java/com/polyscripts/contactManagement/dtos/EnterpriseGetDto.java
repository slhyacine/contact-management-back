package com.polyscripts.contactManagement.dtos;

import com.polyscripts.contactManagement.models.Contact;
import com.polyscripts.contactManagement.models.Enterprise;
import lombok.Data;

import java.util.List;

@Data
public class EnterpriseGetDto {

    private String name;
    private String address;
    private Long tva;
    private List<ContactGetDto> contacts;

//    public EnterpriseGetDto(Enterprise enterprise) {
//        this.name = enterprise.getName();
//        this.address = enterprise.getAddress();
//        this.tva = enterprise.getTva();
//        enterprise.getContacts().forEach(contact -> {  });
//    }

}
