package com.rngouveia.customer.infrastructure;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RegistrationRepository extends ReactiveMongoRepository<CustomerDocument, String> {
}
