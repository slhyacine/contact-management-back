package com.polyscripts.contactManagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {

    private HttpStatus status;
    private String message = "";
    private String debugMessage = "";

    public ErrorResponse() {}

}
