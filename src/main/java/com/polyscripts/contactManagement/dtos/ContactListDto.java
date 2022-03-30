package com.polyscripts.contactManagement.dtos;

import lombok.Data;

@Data
public class ContactListDto {

    private Long id;
    private String name;
    private String lastname;
    private String address;
    protected String type;

}
