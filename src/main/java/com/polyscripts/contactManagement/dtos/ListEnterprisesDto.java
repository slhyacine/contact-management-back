package com.polyscripts.contactManagement.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ListEnterprisesDto {

    private List<EnterpriseListDto> content;
    private Long totalElements;

}
