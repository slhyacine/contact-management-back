package com.polyscripts.contactManagement.dtos;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class ContactEmployeeCreateDto {
    private String type;
    private String name;
    private String lastname;
    private String address;
}
