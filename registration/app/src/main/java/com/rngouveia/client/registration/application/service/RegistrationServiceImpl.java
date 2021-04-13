package com.rngouveia.client.registration.application.service;

import com.rngouveia.client.registration.application.port.RegistrationPort;
import com.rngouveia.client.registration.application.service.dto.*;
import com.rngouveia.client.registration.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationConverter converter;

    @Autowired
    private RegistrationPort registrationPort;

    @Override
    public Mono<Client> create(RegisterClientVO vo) {
        return Mono.just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::create)
                ;
    }

    @Override
    public Mono<Client> update(UpdateClientVO vo) {
        return Mono.just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::update)
                ;
    }

    @Override
    public Mono<Client> delete(DeleteClientVO vo) {
        return Mono.just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::delete)
                ;
    }

    @Override
    public Mono<Client> find(FindClientByIdVO vo) {
        return Mono.just(vo)
                .map(converter::toRequest)
                .flatMap(registrationPort::find)
                ;
    }

    @Override
    public Flux<Client> find(FindClientVO vo) {
        return Mono.just(vo)
                .map(converter::toRequest)
                .flatMapMany(registrationPort::find)
                ;
    }
}
