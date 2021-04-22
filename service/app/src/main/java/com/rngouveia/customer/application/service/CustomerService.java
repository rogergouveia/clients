package com.rngouveia.customer.application.service;

import com.rngouveia.customer.application.service.dto.*;
import com.rngouveia.customer.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface CustomerService {
    Mono<Customer> create(@NotNull @Valid CreateCustomerServiceRequest vo);
    Mono<Customer> update(@NotNull @Valid UpdateCustomerServiceRequest vo);
    Mono<Customer> disable(@NotNull @Valid DisableCustomerServiceRequest vo);
    Mono<Customer> find(@NotNull @Valid FindCustomerByIdServiceRequest vo);
    Flux<Customer> find(@NotNull @Valid FindCustomersServiceRequest vo);
}
