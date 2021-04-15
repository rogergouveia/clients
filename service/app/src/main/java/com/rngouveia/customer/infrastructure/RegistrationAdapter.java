package com.rngouveia.customer.infrastructure;

import com.rngouveia.customer.application.port.RegistrationPort;
import com.rngouveia.customer.application.port.dto.request.*;
import com.rngouveia.customer.application.port.dto.response.CustomerPortResponse;
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
    private CustomerDocumentConverter converter;

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<CustomerPortResponse> create(CreateCustomerPortRequest vo) {
        return Mono
                .just(vo)
                .map(converter::toDocument)
                .flatMap(repository::insert)
                ;
    }

    @Override
    public Mono<CustomerPortResponse> update(UpdateCustomerPortRequest request) {
        return find(request.getId())
                .map(updateData(request))
                .flatMap(repository::save)
                ;
    }

    private Function<CustomerDocument, CustomerDocument> updateData(UpdateCustomerPortRequest request){
        return customerDocument -> {
            CustomerDocument document = new CustomerDocument();
            document.setId(customerDocument.getId());
            document.setName(request.getName().orElse(customerDocument.getName()));
            document.setEmail(request.getEmail().orElse(customerDocument.getEmail()));
            document.setAge(request.getAge().orElse(customerDocument.getAge()));
            return document;
        };
    }

    @Override
    public Mono<CustomerPortResponse> find(FindCustomerByIdPortRequest vo) {
        return Mono
                .just(vo.getId())
                .flatMap(this::find)
                ;
    }

    private Mono<CustomerDocument> find(String id){
        return Mono
                .just(id)
                .flatMap(repository::findById)
                ;
    }

    @Override
    public Flux<CustomerPortResponse> find(FindCustomersPortRequest vo) {
        return Mono
                .just(vo)
                .map(toQuery())
                .flatMapMany(this::find)
                ;
    }

    private Function<FindCustomersPortRequest, Query> toQuery(){
        return request -> {
            Criteria criteria = Criteria.where("").is(true);
            request.getNameRegex().ifPresent(name -> criteria.and("name").regex(name));
            request.getEmailRegex().ifPresent(email -> criteria.and("email").regex(email));

            request.getAgeMin().ifPresent(ageMin -> criteria.and("age").gte(ageMin));
            request.getAgeMax().ifPresent(ageMax -> criteria.and("age").gte(ageMax));

            return new Query(criteria);
        };
    }

    private Flux<CustomerDocument> find(Query query){
        return mongoTemplate.find(query, CustomerDocument.class);
    }
}
