package com.rngouveia.customer.application.service;

import com.rngouveia.customer.application.port.RegistrationPort;
import com.rngouveia.customer.application.service.dto.*;
import com.rngouveia.customer.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationConverter converter;

    @Autowired
    private RegistrationPort registrationPort;

    @Override
    public Mono<Customer> create(@NotNull @Valid CreateCustomerVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::create)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> update(@NotNull @Valid UpdateCustomerVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::update)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> disable(@NotNull @Valid DisableCustomerVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::update)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> find(@NotNull @Valid FindCustomerByIdVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::find)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Flux<Customer> find(@NotNull @Valid FindCustomersVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMapMany(registrationPort::find)
                .map(converter::toDomain)
                ;
    }
}
