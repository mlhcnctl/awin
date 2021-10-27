package com.awin.awin.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorException;
    private String errorDetails;
}
