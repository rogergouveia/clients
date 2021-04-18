package com.rngouveia.customer.application.service;

import com.rngouveia.customer.application.port.CustomerPort;
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
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerConverter converter;

    @Autowired
    private CustomerPort customerPort;

    @Override
    public Mono<Customer> create(@NotNull @Valid CreateCustomerVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(customerPort::create)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> update(@NotNull @Valid UpdateCustomerVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(customerPort::update)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> disable(@NotNull @Valid DisableCustomerVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(customerPort::update)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Customer> find(@NotNull @Valid FindCustomerByIdVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(customerPort::find)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Flux<Customer> find(@NotNull @Valid FindCustomersVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMapMany(customerPort::find)
                .map(converter::toDomain)
                ;
    }
}