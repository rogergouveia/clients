package com.rngouveia.client.registration.application.service;

import com.rngouveia.client.registration.application.service.dto.*;
import com.rngouveia.client.registration.domain.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface RegistrationService {
    Mono<Client> create(@NotNull @Valid CreateClientVO vo);
    Mono<Client> update(@NotNull @Valid UpdateClientVO vo);
    Mono<Client> find(@NotNull @Valid FindClientByIdVO vo);
    Flux<Client> find(@NotNull @Valid FindClientsVO vo);
}
