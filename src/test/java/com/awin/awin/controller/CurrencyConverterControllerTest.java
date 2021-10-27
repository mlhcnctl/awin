package com.awin.awin.controller;

import com.awin.awin.TestHelper.Helper;
import com.awin.awin.dto.Request;
import com.awin.awin.dto.Response;
import com.awin.awin.service.CurrencyConverterService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyConverterControllerTest extends Helper {

    @MockBean
    private CurrencyConverterService currencyConverterService;

    @Autowired
    public MockMvc mockMvc;

    @BeforeClass
    public static void init() {
        baseAddress = "/api";
    }

    @Test
    public void return_response_when_request_send() throws Exception {
        //given
        Request request = new Request();
        request.setSourceCurrency("USD");
        request.setTargetCurrency("EUR");
        request.setAmount(1);
        Response response = new Response();
        response.setSourceCurrency("USD");
        response.setTargetCurrency("EUR");
        response.setConvertedAmount(0.825786);
        String rates = "{\"AED\":3.672999,\"AFN\":78.538176,\"ALL\":101.519623,\"AMD\":520.681702,\"ANG\":1.796445,\"AOA\":642.000224,\"ARS\":95.123903,\"AUD\":1.297434,\"AWG\":1.8,\"AZN\":1.700805,\"BAM\":1.610904,\"BBD\":2.000001,\"BDT\":84.874914,\"BGN\":1.614875,\"BHD\":0.376911,\"BIF\":1977.564619,\"BMD\":1,\"BND\":1.324592,\"BOB\":6.975754,\"BRL\":5.117324,\"BSD\":1,\"BTC\":0.000026,\"BTN\":73.130199,\"BWP\":10.579683,\"BYN\":2.481716,\"BZD\":2.017346,\"CAD\":1.215847,\"CDF\":1993.691563,\"CHF\":0.898199,\"CLF\":0.026166,\"CLP\":722.000791,\"CNH\":6.393993,\"CNY\":6.398702,\"COP\":3618.221316,\"CRC\":619.592505,\"CUC\":1,\"CUP\":25.750009,\"CVE\":90.875032,\"CZK\":20.986806,\"DJF\":178.142693,\"DKK\":6.140845,\"DOP\":57.00633,\"DZD\":134.071574,\"EGP\":15.637366,\"ERN\":15.002004,\"ETB\":43.568191,\"EUR\":0.825786,\"FJD\":2.049001,\"FKP\":0.708511,\"GBP\":0.708511,\"GEL\":3.160001,\"GGP\":0.708511,\"GHS\":5.805778,\"GIP\":0.708511,\"GMD\":51.100018,\"GNF\":9818.637937,\"GTQ\":7.736388,\"GYD\":209.386288,\"HKD\":7.761828,\"HNL\":24.088924,\"HRK\":6.179182,\"HTG\":92.075787,\"HUF\":287.629124,\"IDR\":14214.510999,\"ILS\":3.253281,\"IMP\":0.708511,\"INR\":73.23379,\"IQD\":1460.319945,\"IRR\":42105.014684,\"ISK\":121.463459,\"JEP\":0.708511,\"JMD\":149.622835,\"JOD\":0.709,\"JPY\":109.728538,\"KES\":107.853278,\"KGS\":84.598982,\"KHR\":4080.03235,\"KMF\":404.375077,\"KPW\":900.000314,\"KRW\":1116.543593,\"KWD\":0.300661,\"KYD\":0.83402,\"KZT\":427.313744,\"LAK\":9454.537998,\"LBP\":1516.842454,\"LKR\":198.163202,\"LRD\":171.50009,\"LSL\":13.577472,\"LYD\":4.458953,\"MAD\":8.84172,\"MDL\":17.717044,\"MGA\":3785.811272,\"MKD\":50.756943,\"MMK\":1647.35881,\"MNT\":2845.999267,\"MOP\":7.999212,\"MRO\":356.999952,\"MRU\":36.113304,\"MUR\":40.757402,\"MVR\":15.450006,\"MWK\":801.657451,\"MXN\":19.878517,\"MYR\":4.108001,\"MZN\":62.272027,\"NAD\":13.730005,\"NGN\":412.499324,\"NIO\":35.072237,\"NOK\":8.327916,\"NPR\":117.008233,\"NZD\":1.401169,\"OMR\":0.38501,\"PAB\":1,\"PEN\":3.89653,\"PGK\":3.537553,\"PHP\":47.772311,\"PKR\":155.962078,\"PLN\":3.714784,\"PYG\":6717.862951,\"QAR\":3.653683,\"RON\":4.061785,\"RSD\":96.843822,\"RUB\":72.111959,\"RWF\":997.509547,\"SAR\":3.750301,\"SBD\":7.993654,\"SCR\":16.498838,\"SDG\":430.00015,\"SEK\":8.319408,\"SGD\":1.32602,\"SHP\":0.708511,\"SLL\":10326.203771,\"SOS\":581.362642,\"SRD\":21.077507,\"SSP\":130.260045,\"STD\":20408.503244,\"STN\":20.450007,\"SVC\":8.756963,\"SYP\":1257.835469,\"SZL\":13.636818,\"THB\":31.068675,\"TJS\":11.414361,\"TMT\":3.510002,\"TND\":2.747501,\"TOP\":2.22252,\"TRY\":8.388718,\"TTD\":6.791752,\"TWD\":27.656326,\"TZS\":2320.42141,\"UAH\":27.043052,\"UGX\":3524.86077,\"USD\":1,\"UYU\":43.609543,\"UZS\":10600.221776,\"VES\":3120763.338339,\"VND\":23022.381144,\"VUV\":108.440842,\"WST\":2.521974,\"XAF\":541.680327,\"XAG\":0.035813,\"XAU\":0.000533,\"XCD\":2.702551,\"XDR\":0.693686,\"XOF\":541.680327,\"XPD\":0.000359,\"XPF\":98.542522,\"XPT\":0.00087,\"YER\":250.05006,\"ZAR\":13.700352,\"ZMW\":22.586509,\"ZWL\":322.000112}";
        JsonNode object = stringToJSONObject(rates);
        response.setRates(object);
        when(currencyConverterService.convertSourceToTarget(request)).thenReturn(response);

        //when
        ResultActions perform = mockMvc.perform(get(baseAddress + "/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(request)));

        //then
        perform.andExpect(status().isOk());
        Response res = new ObjectMapper().readValue(perform.andReturn().getResponse()
                .getContentAsString(), Response.class);
        assertThat(res.getSourceCurrency()).isEqualTo(response.getSourceCurrency());
        assertThat(res.getTargetCurrency()).isEqualTo(response.getTargetCurrency());
        assertThat(res.getConvertedAmount()).isEqualTo(response.getConvertedAmount());
        assertThat(res.getRates()).isEqualTo(response.getRates());

        verify(currencyConverterService).convertSourceToTarget(request);

    }

}
