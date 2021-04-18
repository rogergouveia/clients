package com.rngouveia.customer.api;

import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest;
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest;
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest;
import com.rngouveia.customer.api.dto.response.CustomerApiResponse;
import com.rngouveia.customer.application.service.CustomerService;
import com.rngouveia.customer.application.service.dto.DisableCustomerVO;
import com.rngouveia.customer.application.service.dto.FindCustomerByIdVO;
import com.rngouveia.customer.application.service.dto.UpdateCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerConverterApi converter;

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
                .map(FindCustomerByIdVO::create)
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
        UpdateCustomerVO vo = converter.toVO(id, request);
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
                .map(DisableCustomerVO::create)
                .flatMap(service::disable)
                .map(converter::toApiDto)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                ;
    }

}
