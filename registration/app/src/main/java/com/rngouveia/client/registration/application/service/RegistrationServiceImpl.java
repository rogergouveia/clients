package com.rngouveia.client.registration.application.service;

import com.rngouveia.client.registration.application.port.RegistrationPort;
import com.rngouveia.client.registration.application.service.dto.*;
import com.rngouveia.client.registration.domain.Client;
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
    public Mono<Client> create(@NotNull @Valid CreateClientVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::create)
                ;
    }

    @Override
    public Mono<Client> update(@NotNull @Valid UpdateClientVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::update)
                ;
    }

    @Override
    public Mono<Client> find(@NotNull @Valid FindClientByIdVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::find)
                ;
    }

    @Override
    public Flux<Client> find(@NotNull @Valid FindClientsVO vo) {
        return Mono
                .just(vo)
                .map(converter::toRequest)
                .flatMapMany(registrationPort::find)
                ;
    }
}
