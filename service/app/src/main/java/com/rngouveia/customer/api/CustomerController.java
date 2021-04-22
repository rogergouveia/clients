package com.rngouveia.customer.api;

import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest;
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest;
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest;
import com.rngouveia.customer.api.dto.response.CustomerApiResponse;
import com.rngouveia.customer.application.service.CustomerService;
import com.rngouveia.customer.application.service.dto.DisableCustomerServiceRequest;
import com.rngouveia.customer.application.service.dto.FindCustomerByIdServiceRequest;
import com.rngouveia.customer.application.service.dto.UpdateCustomerServiceRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@RequestMapping("/customers")
@RestController
@Validated
public class CustomerController {
    private CustomerService service;
    private CustomerConverterApi converter;

    public CustomerController(CustomerService service, CustomerConverterApi converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public Flux<CustomerApiResponse> find(@NotNull FindCustomersApiRequest criteria){
        return Mono
                .just(criteria)
                .map(converter::toVO)
                .flatMapMany(service::find)
                .map(converter::toApiDto)
                ;
    }

    @GetMapping(path = "/{id}")
    public Mono<CustomerApiResponse> findById(@PathVariable String id){
        return Mono
                .just(id)
                .map(FindCustomerByIdServiceRequest::create)
                .flatMap(service::find)
                .map(converter::toApiDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                ;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public Mono<CustomerApiResponse> create(@RequestBody CreateCustomerApiRequest request){
        return Mono
                .just(request)
                .map(converter::toVO)
                .flatMap(service::create)
                .map(converter::toApiDto)
                ;
    }

    @PatchMapping(path = "/{id}")
    public Mono<CustomerApiResponse> update(@PathVariable String id,
                                            @RequestBody UpdateCustomerApiRequest request){
        UpdateCustomerServiceRequest vo = converter.toVO(id, request);
        return Mono
                .just(vo)
                .flatMap(service::update)
                .map(converter::toApiDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                ;
    }

    @PutMapping(path = "/{id}/disabled")
    public Mono<CustomerApiResponse> disable(@PathVariable String id){
        return Mono
                .just(id)
                .map(DisableCustomerServiceRequest::create)
                .flatMap(service::disable)
                .map(converter::toApiDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                ;
    }

}
