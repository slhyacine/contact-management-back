package com.polyscripts.contactManagement.dtos;

import lombok.Data;

@Data
public class ContactFreelanceCreateDto {
    private String type;
    private String name;
    private String lastname;
    private String address;
    private Long tva;
}
