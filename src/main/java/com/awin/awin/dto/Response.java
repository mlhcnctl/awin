package com.awin.awin.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class Response {
    private String sourceCurrency;
    private String targetCurrency;
    private double convertedAmount;
    private JsonNode rates;
}
