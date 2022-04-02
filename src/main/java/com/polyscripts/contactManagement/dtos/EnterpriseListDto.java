package com.polyscripts.contactManagement.dtos;

import lombok.Data;

@Data
public class EnterpriseListDto {

    private Long id;
    private String name;
    private String address;
    private Long tva;

}
