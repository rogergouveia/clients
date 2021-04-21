package com.rngouveia.customer.helper.factory

import com.rngouveia.customer.application.port.dto.request.CreateCustomerPortRequest
import com.rngouveia.customer.domain.Customer
import com.rngouveia.customer.infrastructure.CustomerDocument

trait CustomerDocumentFactory {

    CustomerDocument createCustomerDocument(CreateCustomerPortRequest request){
        return createCustomerDocument("customerDocumentId", request.getName(), request.getEmail(), request.getAge(), Customer.Status.ENABLED)
    }

    CustomerDocument createCustomerDocument(String id, String name, String email, Integer age, Customer.Status status){
        CustomerDocument document = new CustomerDocument()
        document.setId(id)
        document.setAge(age)
        document.setEmail(email)
        document.setName(name)
        document.setStatus(status)
        return document
    }
}
