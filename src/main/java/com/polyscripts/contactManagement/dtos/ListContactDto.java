package com.polyscripts.contactManagement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ListContactDto {

    private List<ContactListDto> content;
    private Long totalElements;

}
