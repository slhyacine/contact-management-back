package com.polyscripts.contactManagement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class EnterpriseGetDto {

    private String name;
    private String address;
    private Long tva;
    private List<ContactListDto> contacts;

}
