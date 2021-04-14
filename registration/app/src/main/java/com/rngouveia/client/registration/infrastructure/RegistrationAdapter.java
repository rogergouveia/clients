package com.rngouveia.client.registration.infrastructure;

import com.rngouveia.client.registration.application.port.*;
import com.rngouveia.client.registration.application.port.dto.*;
import com.rngouveia.client.registration.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class RegistrationAdapter implements RegistrationPort {

    @Autowired
    private RegistrationRepository repository;

    @Autowired
    private ClientDocumentConverter converter;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Client> create(CreateClientPortRequest vo) {
        return Mono
                .just(vo)
                .map(converter::toDocument)
                .flatMap(repository::insert)
                .map(converter::toDomain)
                ;
    }

    @Override
    public Mono<Client> update(UpdateClientPortRequest request) {
        return find(request.getId())
                .map(updateData(request))
                .flatMap(repository::save)
                .map(converter::toDomain)
                ;
    }

    private Function<ClientDocument, ClientDocument> updateData(UpdateClientPortRequest request){
        return clientDocument -> {
            ClientDocument document = new ClientDocument();
            document.setId(clientDocument.getId());
            document.setName(request.getName().orElse(clientDocument.getName()));
            document.setEmail(request.getEmail().orElse(clientDocument.getEmail()));
            document.setAge(request.getAge().orElse(clientDocument.getAge()));
            return document;
        };
    }

    @Override
    public Mono<Client> find(FindClientByIdPortRequest vo) {
        return Mono
                .just(vo.getId())
                .flatMap(this::find)
                .map(converter::toDomain)
                ;
    }

    private Mono<ClientDocument> find(String id){
        return Mono
                .just(id)
                .flatMap(repository::findById)
                ;
    }

    @Override
    public Flux<Client> find(FindClientsPortRequest vo) {
        return Mono
                .just(vo)
                .map(toQuery())
                .flatMapMany(this::find)
                .map(converter::toDomain)
                ;
    }

    private Function<FindClientsPortRequest, Query> toQuery(){
        return request -> {
            Criteria criteria = Criteria.where("").is(true);
            request.getNameRegex().ifPresent(name -> criteria.and("name").regex(name));
            request.getEmailRegex().ifPresent(email -> criteria.and("email").regex(email));

            request.getAgeMin().ifPresent(ageMin -> criteria.and("age").gte(ageMin));
            request.getAgeMax().ifPresent(ageMax -> criteria.and("age").gte(ageMax));

            return new Query(criteria);
        };
    }

    private Flux<ClientDocument> find(Query query){
        return mongoTemplate.find(query, ClientDocument.class);
    }
}
