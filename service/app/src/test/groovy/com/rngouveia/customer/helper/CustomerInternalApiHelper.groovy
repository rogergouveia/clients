package com.rngouveia.customer.helper

import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest
import com.rngouveia.customer.helper.wiremock.WebTestClientSpringHelper
import org.springframework.test.web.reactive.server.WebTestClient

trait CustomerInternalApiHelper extends WebTestClientSpringHelper{

    WebTestClient.ResponseSpec findCustomers(FindCustomersApiRequest request){
        return getWebTestClient()
                .get()
                .uri({builder ->
                    builder
                    .path("/customers")
                    .queryParamIfPresent("nameRegex", request.getNameRegex())
                    .queryParamIfPresent("ageMin", request.getAgeMin())
                    .queryParamIfPresent("ageMax", request.getAgeMax())
                    .queryParamIfPresent("emailRegex", request.getEmailRegex())
                    .build()
                })
                .exchange()
    }

    WebTestClient.ResponseSpec findByIdCustomer(String id){
        return getWebTestClient()
                .get()
                .uri("/customers/${id}")
                .exchange()
    }

    WebTestClient.ResponseSpec createCustomer(CreateCustomerApiRequest request){
        return getWebTestClient()
                .post()
                .uri("/customers")
                .bodyValue(request)
                .exchange()
    }

    WebTestClient.ResponseSpec updateCustomer(String id, UpdateCustomerApiRequest request){
        return getWebTestClient()
                .patch()
                .uri("/customers/${id}")
                .bodyValue(request)
                .exchange()
    }

    WebTestClient.ResponseSpec disableCustomer(String id){
        return getWebTestClient()
                .put()
                .uri("/customers/${id}/disabled")
                .exchange()
    }



}