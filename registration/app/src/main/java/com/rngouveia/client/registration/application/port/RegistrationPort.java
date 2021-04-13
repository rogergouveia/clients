package com.rngouveia.client.registration.application.port;

import com.rngouveia.client.registration.application.port.dto.*;
import com.rngouveia.client.registration.domain.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RegistrationPort {
    Mono<Client> create(RegisterClientPortRequest vo);
    Mono<Client> update(UpdateClientPortRequest vo);
    Mono<Client> delete(DeleteClientPortRequest vo);
    Mono<Client> find(FindClientByIdPortRequest vo);
    Flux<Client> find(FindClientPortRequest vo);
}
