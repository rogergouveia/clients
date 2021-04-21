package com.rngouveia.customer.helper.factory


import com.rngouveia.customer.domain.Customer
import com.rngouveia.customer.infrastructure.CustomerDocument

class CustomerDocumentFactory {

    static CustomerDocument create(String id, String name, String email, Integer age, Customer.Status status){
        CustomerDocument document = new CustomerDocument()
        document.setId(id)
        document.setAge(age)
        document.setEmail(email)
        document.setName(name)
        document.setStatus(status)
        return document
    }
}
