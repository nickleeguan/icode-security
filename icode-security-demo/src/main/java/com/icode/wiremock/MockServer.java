package com.icode.wiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class MockServer {
    public static void main(String[] args) {

        configureFor("127.0.0.1",8062);
        removeAllMappings();
        //测试桩
        stubFor(get(urlEqualTo("/order/1")).willReturn(aResponse().withBody("{\"id\":1}")
                .withStatus(200)));
    }
}
