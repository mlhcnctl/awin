package com.awin.awin.util;

import com.awin.awin.dto.Request;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static com.awin.awin.util.Constant.*;

@Component
public class UrlMaker {

    public String prepareUrlForListAllCurrencies(Request request) {
        UriComponentsBuilder builder = initilazeBuilder();
        buildUrl(builder);
        addPath(builder, "/latest");
        addParam(builder, "base", request.getSourceCurrency());
        return builder.toUriString();
    }

    public String prepareUrlForConvertCurrency(Request request) {
        UriComponentsBuilder builder = initilazeBuilder();
        buildUrl(builder);
        addPath(builder, "/convert");
        addParam(builder, "from", request.getSourceCurrency());
        addParam(builder, "to", request.getTargetCurrency());
        addParam(builder, "amount", String.valueOf(request.getAmount()));
        return builder.toUriString();
    }

    public UriComponentsBuilder initilazeBuilder() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        return builder;
    }

    public UriComponents buildUrl(UriComponentsBuilder builder) {
        return builder.scheme(WEB_URL_SCHEMA)
                .host(BASE_URL_HOST)
                .build();
    }

    public String addPath(UriComponentsBuilder builder, String path) {
        builder.path(path);
        return builder.toUriString();
    }

    public String addParam(UriComponentsBuilder builder, String name, String value) {
        builder.queryParam(name, value);
        return builder.toUriString();
    }

}
