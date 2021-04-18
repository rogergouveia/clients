package com.rngouveia.customer.api;

import com.rngouveia.customer.api.dto.response.CustomerApiResponse;
import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest;
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest;
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest;
import com.rngouveia.customer.application.service.dto.FindCustomersVO;
import com.rngouveia.customer.application.service.dto.CreateCustomerVO;
import com.rngouveia.customer.application.service.dto.UpdateCustomerVO;
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

    public FindCustomersVO toVO(FindCustomersApiRequest criteria){
        return FindCustomersVO.newInstance()
                .withNameRegex(criteria.getNameRegex().orElse(null))
                .withAgeMin(criteria.getAgeMin().orElse(null))
                .withAgeMax(criteria.getAgeMax().orElse(null))
                .withEmailRegex(criteria.getEmailRegex().orElse(null))
                .build()
                ;
    }

    public UpdateCustomerVO toVO(String id, UpdateCustomerApiRequest request){
        return UpdateCustomerVO.newInstance()
                .withId(id)
                .withName(request.getName().orElse(null))
                .withEmail(request.getEmail().orElse(null))
                .withAge(request.getAge().orElse(null))
                .build()
                ;
    }

    public CreateCustomerVO toVO(CreateCustomerApiRequest request){
        return CreateCustomerVO.newInstance()
                .withName(request.getName())
                .withAge(request.getAge())
                .withEmail(request.getEmail())
                .build()
                ;
    }
}
