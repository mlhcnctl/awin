package com.awin.awin.TestHelper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {

    public static String baseAddress;

    public static String asJsonString(final Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public JsonNode stringToJSONObject(String jsonString) throws JsonProcessingException {
        ObjectMapper jacksonObjMapper = new ObjectMapper();
        return jacksonObjMapper.readTree(jsonString);
    }
}
