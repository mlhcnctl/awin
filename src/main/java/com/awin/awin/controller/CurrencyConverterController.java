package com.awin.awin.controller;

import com.awin.awin.dto.Request;
import com.awin.awin.dto.Response;
import com.awin.awin.service.CurrencyConverterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    @GetMapping("/convert")
    public ResponseEntity<Response> convert(@Valid @RequestBody Request request) throws IOException {
        return ResponseEntity.ok(currencyConverterService.convertSourceToTarget(request));
    }
}
