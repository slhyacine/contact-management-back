package com.polyscripts.contactManagement.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {

    private HttpStatus status;
    private String message = "";
    private String debugMessage = "";
    private List<String> subErrors = new ArrayList<>();

    public ErrorResponse() {
    }

}
