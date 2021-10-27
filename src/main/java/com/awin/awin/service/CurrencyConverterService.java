package com.awin.awin.service;

import com.awin.awin.dto.Request;
import com.awin.awin.dto.Response;
import com.awin.awin.util.ExchangeRate;
import com.awin.awin.util.UrlMaker;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CurrencyConverterService {

    private final UrlMaker urlMaker;
    private final ExchangeRate exchangeRate;

    public Response convertSourceToTarget(Request request) throws IOException {

        String urlForListAllCurrencies = urlMaker.prepareUrlForListAllCurrencies(request);
        String urlForConvertCurrencies = urlMaker.prepareUrlForConvertCurrency(request);

        JsonNode allRates = exchangeRate.getAllRates(urlForListAllCurrencies);
        double convertedMoney = exchangeRate.getConvertedMoney(urlForConvertCurrencies);

        Response response = new Response();
        response.setSourceCurrency(request.getSourceCurrency());
        response.setTargetCurrency(request.getTargetCurrency());
        response.setConvertedAmount(convertedMoney);
        response.setRates(allRates);

        return response;
    }

}
