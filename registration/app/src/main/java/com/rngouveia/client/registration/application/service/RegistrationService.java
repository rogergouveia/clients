package com.rngouveia.client.registration.application.service;

import com.rngouveia.client.registration.application.service.dto.*;
import com.rngouveia.client.registration.domain.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RegistrationService {

    Mono<Client> create(RegisterClientVO vo);
    Mono<Client> update(UpdateClientVO vo);
    Mono<Client> delete(DeleteClientVO vo);
    Mono<Client> find(FindClientByIdVO vo);
    Flux<Client> find(FindClientVO vo);
}
