package com.rngouveia.client.registration.infrastructure;

import com.rngouveia.client.registration.application.port.*;
import com.rngouveia.client.registration.application.port.dto.*;
import com.rngouveia.client.registration.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RegistrationAdapter implements RegistrationPort {

    @Autowired
    private RegistrationRepository repository;

    @Override
    public Mono<Client> create(RegisterClientPortRequest vo) {
        return null;
    }

    @Override
    public Mono<Client> update(UpdateClientPortRequest vo) {
        return null;
    }

    @Override
    public Mono<Client> delete(DeleteClientPortRequest vo) {
        return null;
    }

    @Override
    public Mono<Client> find(FindClientByIdPortRequest vo) {
        return null;
    }

    @Override
    public Flux<Client> find(FindClientPortRequest vo) {
        return null;
    }
}
