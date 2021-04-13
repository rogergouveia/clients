package com.rngouveia.client.registration.infrastructure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RegistrationRepository extends ReactiveMongoRepository<ClientDocument, String> {
}
