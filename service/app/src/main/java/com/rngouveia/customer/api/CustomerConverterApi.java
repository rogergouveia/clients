package com.rngouveia.customer.api;

import com.rngouveia.customer.api.dto.response.CustomerApiResponse;
import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest;
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest;
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest;
import com.rngouveia.customer.application.service.dto.FindCustomersServiceRequest;
import com.rngouveia.customer.application.service.dto.CreateCustomerServiceRequest;
import com.rngouveia.customer.application.service.dto.UpdateCustomerServiceRequest;
import com.rngouveia.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverterApi {
    public CustomerApiResponse toApiDto(Customer customer){
        return CustomerApiResponse.newInstance()
                .withId(customer.getId())
                .withName(customer.getName())
                .withEmail(customer.getEmail())
                .withAge(customer.getAge())
                .withStatus(customer.getStatus())
                .build()
                ;
    }

    public FindCustomersServiceRequest toVO(FindCustomersApiRequest criteria){
        return FindCustomersServiceRequest.newInstance()
                .withNameRegex(criteria.getNameRegex().orElse(null))
                .withAgeMin(criteria.getAgeMin().orElse(null))
                .withAgeMax(criteria.getAgeMax().orElse(null))
                .withEmailRegex(criteria.getEmailRegex().orElse(null))
                .build()
                ;
    }

    public UpdateCustomerServiceRequest toVO(String id, UpdateCustomerApiRequest request){
        return UpdateCustomerServiceRequest.newInstance()
                .withId(id)
                .withName(request.getName().orElse(null))
                .withEmail(request.getEmail().orElse(null))
                .withAge(request.getAge().orElse(null))
                .build()
                ;
    }

    public CreateCustomerServiceRequest toVO(CreateCustomerApiRequest request){
        return CreateCustomerServiceRequest.newInstance()
                .withName(request.getName())
                .withAge(request.getAge())
                .withEmail(request.getEmail())
                .build()
                ;
    }
}
