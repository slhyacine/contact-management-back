package com.polyscripts.contactManagement.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenState {
    private String access_token;
    private Long expires_in;
}
