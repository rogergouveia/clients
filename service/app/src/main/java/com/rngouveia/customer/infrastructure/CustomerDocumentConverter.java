package com.rngouveia.customer.infrastructure;

import com.rngouveia.customer.application.port.dto.request.CreateCustomerPortRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerDocumentConverter {

    public CustomerDocument toDocument(CreateCustomerPortRequest request){
        CustomerDocument document = new CustomerDocument();
        document.setAge(request.getAge());
        document.setEmail(request.getEmail());
        document.setName(request.getName());
        document.setStatus(request.getStatus());
        return document;
    }

}
