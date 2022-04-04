package com.polyscripts.contactManagement.dtos;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ContactEmployeeCreateDto {
    private String type;
    @NotNull
    @NotBlank(message = "name is required")
    private String name;
    @NotNull
    @NotBlank(message = "lastname is required")
    private String lastname;
    private String address;
}
