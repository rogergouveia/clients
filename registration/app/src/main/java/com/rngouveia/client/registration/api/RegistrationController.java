package com.rngouveia.client.registration.api;

import com.rngouveia.client.registration.application.service.RegistrationService;
import com.rngouveia.client.registration.application.service.dto.FindClientByIdVO;
import com.rngouveia.client.registration.application.service.dto.UpdateClientVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@RequestMapping("/clients")
@RestController
@Validated
public class RegistrationController {
    @Autowired
    private RegistrationService service;

    @Autowired
    private ClientConverterApi converter;

    @GetMapping
    public Flux<ClientApiDto> find(@NotNull FindClientsCriteria criteria){
        return Mono
                .just(criteria)
                .map(converter::toVO)
                .flatMapMany(service::find)
                .map(converter::toApiDto)
                ;
    }

    @GetMapping(path = "/{id}")
    public Mono<ClientApiDto> findById(@PathVariable String id){
        return Mono
                .just(id)
                .map(FindClientByIdVO::create)
                .flatMap(service::find)
                .map(converter::toApiDto)
                ;
    }

    @PostMapping
    public Mono<ClientApiDto> create(@RequestBody CreateClientApiRequest request){
        return Mono
                .just(request)
                .map(converter::toVO)
                .flatMap(service::create)
                .map(converter::toApiDto)
                ;
    }

    @PatchMapping(path = "/{id}")
    public Mono<ClientApiDto> update(@PathVariable String id,
                                     @RequestBody UpdateClientApiRequest request){
        UpdateClientVO vo = converter.toVO(id, request);
        return Mono
                .just(vo)
                .flatMap(service::update)
                .map(converter::toApiDto)
                ;
    }
}
