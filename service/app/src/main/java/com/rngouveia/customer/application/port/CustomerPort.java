package com.rngouveia.customer.application.port;

import com.rngouveia.customer.application.port.dto.request.CreateCustomerPortRequest;
import com.rngouveia.customer.application.port.dto.request.FindCustomerByIdPortRequest;
import com.rngouveia.customer.application.port.dto.request.FindCustomersPortRequest;
import com.rngouveia.customer.application.port.dto.request.UpdateCustomerPortRequest;
import com.rngouveia.customer.application.port.dto.response.CustomerPortResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerPort {
    Mono<CustomerPortResponse> create(CreateCustomerPortRequest request);
    Mono<CustomerPortResponse> update(UpdateCustomerPortRequest request);
    Mono<CustomerPortResponse> find(FindCustomerByIdPortRequest request);
    Flux<CustomerPortResponse> find(FindCustomersPortRequest request);
}
