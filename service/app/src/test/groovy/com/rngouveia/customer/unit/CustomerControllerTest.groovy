package com.rngouveia.customer.unit

import com.rngouveia.customer.api.CustomerController
import com.rngouveia.customer.api.CustomerConverterApi
import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest
import com.rngouveia.customer.api.dto.response.CustomerApiResponse
import com.rngouveia.customer.application.service.CustomerService
import com.rngouveia.customer.application.service.dto.CreateCustomerServiceRequest
import com.rngouveia.customer.application.service.dto.DisableCustomerServiceRequest
import com.rngouveia.customer.application.service.dto.FindCustomerByIdServiceRequest
import com.rngouveia.customer.application.service.dto.FindCustomersServiceRequest
import com.rngouveia.customer.application.service.dto.UpdateCustomerServiceRequest
import com.rngouveia.customer.domain.Customer
import com.rngouveia.customer.helper.factory.FindCustomersApiRequestFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification


class CustomerControllerTest extends Specification {
    private CustomerController controller
    private CustomerService customerServiceMock

    def setup(){
        customerServiceMock = Mock()
        controller = new CustomerController(customerServiceMock, new CustomerConverterApi())
    }

    def "Should call create customer"(){
        given:
        CreateCustomerApiRequest request = CreateCustomerApiRequest.newInstance().withName("anyName").withAge(99).withEmail("any@mail.com").build()

        when: "mocked customerService is called for creating customer"
        CreateCustomerServiceRequest serviceRequest = CreateCustomerServiceRequest.newInstance().withName(request.getName()).withEmail(request.getEmail()).withAge(request.getAge()).build()
        Customer serviceResponse = Customer.newInstance().withId("customerId").withName("name").withEmail("mail").withAge(12).withStatus(Customer.Status.ENABLED).build()
        customerServiceMock.create(serviceRequest) >> Mono.just(serviceResponse)

        and: "controller is called to create customer with the request"
        CustomerApiResponse response = controller.create(request).block()

        then:
        response != null
        response.getId() == serviceResponse.getId()
        response.getName() == serviceResponse.getName()
        response.getEmail() == serviceResponse.getEmail()
        response.getAge() == serviceResponse.getAge()
        response.getStatus() == serviceResponse.getStatus()
    }

    def "Should call update customer"(){
        given:
        String customerId = "customerId"
        UpdateCustomerApiRequest request = UpdateCustomerApiRequest.newInstance().withName("anyName").withAge(99).withEmail("any@mail.com").build()

        when: "mocked customerService is called for updating customer"
        UpdateCustomerServiceRequest serviceRequest = UpdateCustomerServiceRequest.newInstance().withId(customerId).withName(request.getName().orElse(null)).withEmail(request.getEmail().orElse(null)).withAge(request.getAge().orElse(null)).build()
        Customer serviceResponse = Customer.newInstance().withId(customerId).withName("name").withEmail("mail").withAge(12).withStatus(Customer.Status.ENABLED).build()
        customerServiceMock.update(serviceRequest) >> Mono.just(serviceResponse)

        and: "controller is called to update customer with the request"
        CustomerApiResponse response = controller.update(customerId, request).block()

        then:
        response != null
        response.getId() == serviceResponse.getId()
        response.getName() == serviceResponse.getName()
        response.getEmail() == serviceResponse.getEmail()
        response.getAge() == serviceResponse.getAge()
        response.getStatus() == serviceResponse.getStatus()
    }

    def "Should call disable customer"(){
        given:
        String customerId = "customerId"

        when: "mocked customerService is called for disabling customer"
        DisableCustomerServiceRequest serviceRequest = DisableCustomerServiceRequest.create(customerId)
        Customer serviceResponse = Customer.newInstance().withId(customerId).withName("name").withEmail("mail").withAge(12).withStatus(Customer.Status.ENABLED).build()
        customerServiceMock.disable(serviceRequest) >> Mono.just(serviceResponse)

        and: "controller is called to disable customer with the request"
        CustomerApiResponse response = controller.disable(customerId).block()

        then:
        response != null
        response.getId() == serviceResponse.getId()
        response.getName() == serviceResponse.getName()
        response.getEmail() == serviceResponse.getEmail()
        response.getAge() == serviceResponse.getAge()
        response.getStatus() == serviceResponse.getStatus()
    }

    def "Should call find by id"(){
        given:
        String customerId = "customerId"

        when: "mocked customerService is called for find customer by id"
        FindCustomerByIdServiceRequest serviceRequest = FindCustomerByIdServiceRequest.create(customerId)
        Customer serviceResponse = Customer.newInstance().withId(customerId).withName("name").withEmail("mail").withAge(12).withStatus(Customer.Status.ENABLED).build()
        customerServiceMock.find(serviceRequest) >> Mono.just(serviceResponse)

        and: "controller is called to disable customer with the request"
        CustomerApiResponse response = controller.findById(customerId).block()

        then:
        response != null
        response.getId() == serviceResponse.getId()
        response.getName() == serviceResponse.getName()
        response.getEmail() == serviceResponse.getEmail()
        response.getAge() == serviceResponse.getAge()
        response.getStatus() == serviceResponse.getStatus()
    }

    def "Should call find all"(){
        given:
        FindCustomersApiRequest request = FindCustomersApiRequestFactory.create("anyName", "anyMail", 10, 20)

        when: "mocked customerService is called for find customer by id"
        FindCustomersServiceRequest serviceRequest = FindCustomersServiceRequest.newInstance().withNameRegex(request.getNameRegex().orElse(null)).withEmailRegex(request.getEmailRegex().orElse(null)).withAgeMin(request.getAgeMin().orElse(null)).withAgeMax(request.getAgeMax().orElse(null)).build()
        Customer serviceResponse = Customer.newInstance().withId("customerId").withName("name").withEmail("mail").withAge(12).withStatus(Customer.Status.ENABLED).build()
        customerServiceMock.find(serviceRequest) >> Flux.fromIterable([serviceResponse])

        and: "controller is called to disable customer with the request"
        List<CustomerApiResponse> response = controller.find(request).collectList().block()

        then:
        response != null
        response.size() == 1
        CustomerApiResponse apiResponse = response.iterator().next()
        apiResponse.getId() == serviceResponse.getId()
        apiResponse.getName() == serviceResponse.getName()
        apiResponse.getEmail() == serviceResponse.getEmail()
        apiResponse.getAge() == serviceResponse.getAge()
        apiResponse.getStatus() == serviceResponse.getStatus()
    }
}