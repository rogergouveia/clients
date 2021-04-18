package com.rngouveia.customer.application.service;

import com.rngouveia.customer.application.service.dto.*;
import com.rngouveia.customer.domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface CustomerService {
    Mono<Customer> create(@NotNull @Valid CreateCustomerVO vo);
    Mono<Customer> update(@NotNull @Valid UpdateCustomerVO vo);
    Mono<Customer> disable(@NotNull @Valid DisableCustomerVO vo);
    Mono<Customer> find(@NotNull @Valid FindCustomerByIdVO vo);
    Flux<Customer> find(@NotNull @Valid FindCustomersVO vo);
}
