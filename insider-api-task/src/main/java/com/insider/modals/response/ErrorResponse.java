package com.insider.modals.response;

import lombok.Data;

@Data
public class ErrorResponse {

    private Integer code;
    private String type;
    private String message;
}
