package com.rngouveia.client.registration.infrastructure;

import com.rngouveia.client.registration.application.port.dto.CreateClientPortRequest;
import com.rngouveia.client.registration.domain.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDocumentConverter {

    public ClientDocument toDocument(CreateClientPortRequest request){
        ClientDocument document = new ClientDocument();
        document.setAge(request.getAge());
        document.setEmail(request.getEmail());
        document.setName(request.getName());
        return document;
    }

    public Client toDomain(ClientDocument document){
        return Client.newInstance()
                .withId(document.getId())
                .withName(document.getName())
                .withAge(document.getAge())
                .withEmail(document.getEmail())
                .build()
                ;
    }
}
