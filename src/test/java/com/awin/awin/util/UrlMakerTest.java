package com.awin.awin.util;

import com.awin.awin.dto.Request;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import static com.awin.awin.util.Constant.BASE_URL_HOST;
import static com.awin.awin.util.Constant.WEB_URL_SCHEMA;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UrlMakerTest {

    @Autowired
    private UrlMaker urlMaker;

    @Test
    public void prepare_url_for_all_list_of_currencies() {
        //given
        Request request = new Request();
        request.setSourceCurrency("USD");
        request.setTargetCurrency("EUR");
        request.setAmount(1);
        String urlForListAllCurrencies = "https://api.exchangerate.host/latest?base=USD";

        //when
        String response = urlMaker.prepareUrlForListAllCurrencies(request);

        //then
        assertThat(response).isEqualTo(urlForListAllCurrencies);
    }

    @Test
    public void prepare_url_for_convert_currency() {
        //given
        Request request = new Request();
        request.setSourceCurrency("USD");
        request.setTargetCurrency("EUR");
        request.setAmount(1);
        String urlForConvertCurrencies = "https://api.exchangerate.host/convert?from=USD&to=EUR&amount=1.0";

        //when
        String response = urlMaker.prepareUrlForConvertCurrency(request);

        //then
        assertThat(response).isEqualTo(urlForConvertCurrencies);

    }

    @Test
    public void initilaze_builder() {
        //given
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();

        //when
        UriComponentsBuilder response = urlMaker.initilazeBuilder();

        //then
        assertThat(response).usingRecursiveComparison().isEqualTo(builder);
    }

    @Test
    public void build_url() {
        //given
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        String expectedUrl = "https://api.exchangerate.host";

        //when
        UriComponents response = urlMaker.buildUrl(builder);

        //then
        assertThat(response.toUriString()).isEqualTo(expectedUrl);
    }

    @Test
    public void add_path() {
        //given
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.scheme(WEB_URL_SCHEMA).host(BASE_URL_HOST).build();
        String expectedUrl = "https://api.exchangerate.host/latest";

        //when
        String response = urlMaker.addPath(builder, "/latest");

        //then
        assertThat(response).isEqualTo(expectedUrl);
    }

    @Test
    public void add_param() {
        //given
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.scheme(WEB_URL_SCHEMA).host(BASE_URL_HOST).build();
        builder.path("/latest");
        String expectedUrl = "https://api.exchangerate.host/latest?base=EUR";

        //when
        String response = urlMaker.addParam(builder, "base", "EUR");

        //then
        assertThat(response).isEqualTo(expectedUrl);

    }

}
