package com.polyscripts.contactManagement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ContactGetDto {

    private String type;
    private String name;
    private String lastname;
    private String address;
    private Long tva;
    private List<EnterpriseListDto> enterprises;

}
