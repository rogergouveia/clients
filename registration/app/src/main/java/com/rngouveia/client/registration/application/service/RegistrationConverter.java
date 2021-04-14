package com.rngouveia.client.registration.application.service;

import com.rngouveia.client.registration.application.port.dto.*;
import com.rngouveia.client.registration.application.service.dto.*;
import org.springframework.stereotype.Component;

@Component
public class RegistrationConverter {

    public CreateClientPortRequest toRequest(CreateClientVO vo){
        return CreateClientPortRequest.newInstance()
                .withName(vo.getName())
                .withAge(vo.getAge())
                .withEmail(vo.getEmail())
                .build()
                ;
    }

    public UpdateClientPortRequest toRequest(UpdateClientVO vo){
        return UpdateClientPortRequest.newInstance()
                .withId(vo.getId())
                .withName(vo.getName().orElse(null))
                .withAge(vo.getAge().orElse(null))
                .withEmail(vo.getEmail().orElse(null))
                .build()
                ;
    }

    public FindClientsPortRequest toRequest(FindClientsVO vo){
        return FindClientsPortRequest.newInstance()
                .withNameRegex(vo.getNameRegex().orElse(null))
                .withAgeMin(vo.getAgeMin().orElse(null))
                .withAgeMax(vo.getAgeMax().orElse(null))
                .withEmailRegex(vo.getEmailRegex().orElse(null))
                .build()
                ;
    }

    public FindClientByIdPortRequest toRequest(FindClientByIdVO vo){
        return FindClientByIdPortRequest.create(vo.getId());
    }
}
