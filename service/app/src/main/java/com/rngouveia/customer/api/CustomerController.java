package com.rngouveia.customer.api;

import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest;
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest;
import com.rngouveia.customer.api.dto.request.UpdateCustomersApiRequest;
import com.rngouveia.customer.api.dto.response.CustomerApiResponse;
import com.rngouveia.customer.application.service.RegistrationService;
import com.rngouveia.customer.application.service.dto.DisableCustomerVO;
import com.rngouveia.customer.application.service.dto.FindCustomerByIdVO;
import com.rngouveia.customer.application.service.dto.UpdateCustomerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@RequestMapping("/customers")
@RestController
@Validated
public class CustomerController {
    @Autowired
    private RegistrationService service;

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
                ;
    }

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
                                            @RequestBody UpdateCustomersApiRequest request){
        UpdateCustomerVO vo = converter.toVO(id, request);
        return Mono
                .just(vo)
                .flatMap(service::update)
                .map(converter::toApiDto)
                ;
    }

    @PutMapping(path = "/{id}/disabled")
    public Mono<CustomerApiResponse> disable(@PathVariable String id){
        return Mono
                .just(id)
                .map(DisableCustomerVO::create)
                .flatMap(service::disable)
                .map(converter::toApiDto)
                ;
    }

}
