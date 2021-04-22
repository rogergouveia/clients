package com.rngouveia.customer.application.service;

import com.rngouveia.customer.application.port.dto.request.CreateCustomerPortRequest;
import com.rngouveia.customer.application.port.dto.request.FindCustomerByIdPortRequest;
import com.rngouveia.customer.application.port.dto.request.FindCustomersPortRequest;
import com.rngouveia.customer.application.port.dto.request.UpdateCustomerPortRequest;
import com.rngouveia.customer.application.port.dto.response.CustomerPortResponse;
import com.rngouveia.customer.application.service.dto.*;
import com.rngouveia.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerConverter {

    public CreateCustomerPortRequest toRequest(CreateCustomerVO vo){
        return CreateCustomerPortRequest.newInstance()
                .withName(vo.getName())
                .withAge(vo.getAge())
                .withEmail(vo.getEmail())
                .withStatus(Customer.Status.ENABLED)
                .build()
                ;
    }

    public UpdateCustomerPortRequest toRequest(UpdateCustomerVO vo){
        return UpdateCustomerPortRequest.newInstance()
                .withId(vo.getId())
                .withName(vo.getName().orElse(null))
                .withAge(vo.getAge().orElse(null))
                .withEmail(vo.getEmail().orElse(null))
                .withStatus(null)
                .build()
                ;
    }

    public UpdateCustomerPortRequest toRequest(DisableCustomerVO vo){
        return UpdateCustomerPortRequest.newInstance()
                .withId(vo.getId())
                .withStatus(Customer.Status.DISABLED)
                .build()
                ;
    }

    public FindCustomersPortRequest toRequest(FindCustomersVO vo){
        return FindCustomersPortRequest.newInstance()
                .withNameRegex(vo.getNameRegex().orElse(null))
                .withAgeMin(vo.getAgeMin().orElse(null))
                .withAgeMax(vo.getAgeMax().orElse(null))
                .withEmailRegex(vo.getEmailRegex().orElse(null))
                .build()
                ;
    }

    public FindCustomerByIdPortRequest toRequest(FindCustomerByIdVO vo){
        return FindCustomerByIdPortRequest.create(vo.getId());
    }

    public Customer toDomain(CustomerPortResponse customerPortResponse){
        return Customer.newInstance()
                .withId(customerPortResponse.getId())
                .withName(customerPortResponse.getName())
                .withAge(customerPortResponse.getAge())
                .withEmail(customerPortResponse.getEmail())
                .withStatus(customerPortResponse.getStatus())
                .build()
                ;
    }
}
