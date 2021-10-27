package com.awin.awin.util;

import com.awin.awin.exception.custom.InvalidConvertingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ExchangeRate {

    public JsonNode getAllRates(String urlForListAllCurrencies) throws IOException {
        URL url = new URL(urlForListAllCurrencies);
        HttpURLConnection req = (HttpURLConnection) url.openConnection();
        req.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) req.getContent()));
        String rates = root.getAsJsonObject().get("rates").toString();
        JsonNode object = stringToJSONObject(rates);
        return object;
    }

    public double getConvertedMoney (String urlForConvertCurrencies) throws IOException {
        URL url = new URL(urlForConvertCurrencies);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonElement result = root.getAsJsonObject().get("result");

        if (result.isJsonNull())
            throw new InvalidConvertingException();

        double convertedMoney = result.getAsDouble();
        return convertedMoney;
    }

    public JsonNode stringToJSONObject(String jsonString) throws JsonProcessingException {
        ObjectMapper jacksonObjMapper = new ObjectMapper();
        return jacksonObjMapper.readTree(jsonString);
    }

}
