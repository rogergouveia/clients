package com.rngouveia.customer.application.port;

import com.rngouveia.customer.application.port.dto.request.*;
import com.rngouveia.customer.application.port.dto.response.CustomerPortResponse;
import com.rngouveia.customer.application.service.dto.DisableCustomerVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RegistrationPort {
    Mono<CustomerPortResponse> create(CreateCustomerPortRequest request);
    Mono<CustomerPortResponse> update(UpdateCustomerPortRequest request);
    Mono<CustomerPortResponse> find(FindCustomerByIdPortRequest request);
    Flux<CustomerPortResponse> find(FindCustomersPortRequest request);
}
