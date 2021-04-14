package com.rngouveia.client.registration.api;

import com.rngouveia.client.registration.application.service.dto.FindClientsVO;
import com.rngouveia.client.registration.application.service.dto.CreateClientVO;
import com.rngouveia.client.registration.application.service.dto.UpdateClientVO;
import com.rngouveia.client.registration.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverterApi {
    public ClientApiDto toApiDto(Client client){
        return ClientApiDto.newInstance()
                .withId(client.getId())
                .withName(client.getName())
                .withEmail(client.getEmail())
                .withAge(client.getAge())
                .build()
                ;
    }

    public FindClientsVO toVO(FindClientsCriteria criteria){
        return FindClientsVO.newInstance()
                .withNameRegex(criteria.getNameRegex().orElse(null))
                .withAgeMin(criteria.getAgeMin().orElse(null))
                .withAgeMax(criteria.getAgeMax().orElse(null))
                .withEmailRegex(criteria.getEmailRegex().orElse(null))
                .build()
                ;
    }

    public UpdateClientVO toVO(String id, UpdateClientApiRequest request){
        return UpdateClientVO.newInstance()
                .withId(id)
                .withName(request.getName().orElse(null))
                .withEmail(request.getEmail().orElse(null))
                .withAge(request.getAge().orElse(null))
                .build()
                ;
    }

    public CreateClientVO toVO(CreateClientApiRequest request){
        return CreateClientVO.newInstance()
                .withName(request.getName())
                .withAge(request.getAge())
                .withEmail(request.getEmail())
                .build()
                ;
    }
}
