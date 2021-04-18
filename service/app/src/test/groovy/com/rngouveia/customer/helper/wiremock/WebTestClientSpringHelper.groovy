package com.rngouveia.customer.helper.wiremock


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.web.reactive.server.WebTestClient

import java.time.Duration

trait WebTestClientSpringHelper {
    @Autowired
    private ApplicationContext context

    private WebTestClient client

    void resetWebTestClient() {
        client = WebTestClient
                .bindToApplicationContext(context)
                .configureClient()
                .responseTimeout(Duration.ofMinutes(10))
                .build()
    }

    public WebTestClient getWebTestClient() {
        return client;
    }


}
