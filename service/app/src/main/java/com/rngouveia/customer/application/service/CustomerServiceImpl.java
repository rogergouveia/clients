package com.rngouveia.customer.application.service;

import com.rngouveia.customer.application.port.CustomerPort;
import com.rngouveia.customer.application.service.dto.*;
import com.rngouveia.customer.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class CustomerServiceImpl implements CustomerService {

    private CustomerConverter converter;
    private CustomerPort customerPort;
    private CustomerServiceValidator validator;

    public CustomerServiceImpl(CustomerConverter converter, CustomerPort customerPort, CustomerServiceValidator validator) {
        this.converter = converter;
        this.customerPort = customerPort;
        this.validator = validator;
    }

    @Override
    public Mono<Customer> create(@NotNull @Valid CreateCustomerServiceRequest vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(customerPort::create)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> update(@NotNull @Valid UpdateCustomerServiceRequest vo) {
        Mono<Customer> validatedCustomerToUpdate = Mono.just(vo.getId()).map(FindCustomerByIdServiceRequest::create).flatMap(this::find).doOnNext(c -> validator.validate(vo, c));

        return validatedCustomerToUpdate
                .then(Mono.just(vo))
                .map(converter::toRequest)
                .flatMap(customerPort::update)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> disable(@NotNull @Valid DisableCustomerServiceRequest vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(customerPort::update)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> find(@NotNull @Valid FindCustomerByIdServiceRequest vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(customerPort::find)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Flux<Customer> find(@NotNull @Valid FindCustomersServiceRequest vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMapMany(customerPort::find)
                .map(converter::toDomain)
                ;
    }
}
