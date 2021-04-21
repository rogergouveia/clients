package com.rngouveia.customer

import com.rngouveia.customer.application.error.ServiceException
import com.rngouveia.customer.application.port.CustomerPort
import com.rngouveia.customer.application.port.dto.request.CreateCustomerPortRequest
import com.rngouveia.customer.application.port.dto.request.FindCustomerByIdPortRequest
import com.rngouveia.customer.application.port.dto.request.FindCustomersPortRequest
import com.rngouveia.customer.application.port.dto.request.UpdateCustomerPortRequest
import com.rngouveia.customer.application.port.dto.response.CustomerPortResponse
import com.rngouveia.customer.application.service.CustomerConverter
import com.rngouveia.customer.application.service.CustomerServiceImpl
import com.rngouveia.customer.application.service.CustomerServiceValidator
import com.rngouveia.customer.application.service.dto.CreateCustomerVO
import com.rngouveia.customer.application.service.dto.DisableCustomerVO
import com.rngouveia.customer.application.service.dto.FindCustomerByIdVO
import com.rngouveia.customer.application.service.dto.FindCustomersVO
import com.rngouveia.customer.application.service.dto.UpdateCustomerVO
import com.rngouveia.customer.domain.Customer
import com.rngouveia.customer.helper.factory.CustomerDocumentFactory
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import spock.lang.Specification

class CustomerServiceImplTest extends Specification implements CustomerDocumentFactory {
    private CustomerServiceImpl customerService
    private CustomerPort customerPortMock

    def setup() {
        customerPortMock = Mock()
        customerService = new CustomerServiceImpl(new CustomerConverter(), customerPortMock, new CustomerServiceValidator())
    }

    def "Should create customer"() {
        given:
        String name = "fulano de tal"
        String email = "fulaninho@email.com"
        Integer age = 43

        when: "mocked customerPort is called for creating customer"
        Customer.Status status = Customer.Status.ENABLED
        CreateCustomerPortRequest portRequest = CreateCustomerPortRequest.newInstance().withName(name).withEmail(email).withAge(age).withStatus(status).build()
        CustomerPortResponse portResponse = createCustomerDocument("customerId", name, email, age, status)
        customerPortMock.create(portRequest) >> Mono.just(portResponse)

        and: "customerServiceImpl is called for creating customer"
        CreateCustomerVO request = CreateCustomerVO.newInstance().withName(name).withAge(age).withEmail(email).build()
        Customer response = customerService.create(request).block()

        then:
        response != null
        response.getId() == portResponse.getId()
        response.getName() == portResponse.getName()
        response.getEmail() == portResponse.getEmail()
        response.getAge() == portResponse.getAge()
        response.getStatus() == portResponse.getStatus()
    }

    def "Should update customer"(String newName, String newEmail, Integer newAge){
        given:
        CustomerPortResponse savedCustomer = createCustomerDocument("customerId", "anyName", "any@email.com", 1001, Customer.Status.ENABLED)

        when: "mocked customerPort is called for finding customer by id"
        FindCustomerByIdPortRequest findByIdPortRequest = FindCustomerByIdPortRequest.create(savedCustomer.getId())
        customerPortMock.find(findByIdPortRequest) >> Mono.just(savedCustomer)

        and: "mocked customerPort is called for updating customer"
        UpdateCustomerPortRequest updatePortRequest = UpdateCustomerPortRequest.newInstance().withId(savedCustomer.getId()).withName(newName).withEmail(newEmail).withAge(newAge).build()
        CustomerPortResponse updatePortResponse = createCustomerDocument(savedCustomer.getId(), "resultName", "resultEmail", 15, Customer.Status.ENABLED)
        customerPortMock.update(updatePortRequest) >> Mono.just(updatePortResponse)

        and: "customerServiceImpl is called for updating customer"
        UpdateCustomerVO request = UpdateCustomerVO.newInstance().withId(savedCustomer.getId()).withName(newName).withAge(newAge).withEmail(newEmail).build()
        Customer response = customerService.update(request).block()

        then:
        response != null
        response.getId() == updatePortResponse.getId()
        response.getName() == updatePortResponse.getName()
        response.getEmail() == updatePortResponse.getEmail()
        response.getAge() == updatePortResponse.getAge()
        response.getStatus() == updatePortResponse.getStatus()

        where:
        newName    | newEmail         | newAge
        "novoNome" | null             | null
        null       | "novo@Email.com" | null
        null       | null             | 99
        "novoNome" | "novo@Email.com" | 99
    }

    def "Should throw exception when updating customer"(String newName, String newEmail, Integer newAge, Customer.Status status){
        given:
        CustomerPortResponse savedCustomer = createCustomerDocument("customerId", "anyName", "any@email.com", 1001, status)

        when: "mocked customerPort is called for finding customer by id"
        FindCustomerByIdPortRequest findByIdPortRequest = FindCustomerByIdPortRequest.create(savedCustomer.getId())
        customerPortMock.find(findByIdPortRequest) >> Mono.just(savedCustomer)

        and: "customerServiceImpl is called for updating customer"
        UpdateCustomerVO request = UpdateCustomerVO.newInstance().withId(savedCustomer.getId()).withName(newName).withAge(newAge).withEmail(newEmail).build()
        Customer response = customerService.update(request).block()

        then:
        thrown(ServiceException.class)

        where:
        newName    | newEmail         | newAge || status
        "novoNome" | "novo@Email.com" | 99     || Customer.Status.DISABLED
        null       | null             | null   || Customer.Status.ENABLED
    }

    def "Should disable customer"(){
        given:
        String customerId = "customerId"

        when: "mocked customerPort is called for updating customer"
        UpdateCustomerPortRequest updatePortRequest = UpdateCustomerPortRequest.newInstance().withId(customerId).withStatus(Customer.Status.DISABLED).build()
        CustomerPortResponse updatePortResponse = createCustomerDocument(customerId, "anyName", "any@email.com", 1001, Customer.Status.DISABLED)
        customerPortMock.update(updatePortRequest) >> Mono.just(updatePortResponse)

        and: "customerServiceImpl is called for disabling customer"
        DisableCustomerVO request = DisableCustomerVO.create(customerId)
        Customer response = customerService.disable(request).block()

        then:
        response != null
        response.getId() == updatePortResponse.getId()
        response.getName() == updatePortResponse.getName()
        response.getEmail() == updatePortResponse.getEmail()
        response.getAge() == updatePortResponse.getAge()
        response.getStatus() == updatePortResponse.getStatus()

    }

    def "should find customer by id"(){
        given:
        CustomerPortResponse savedCustomer = createCustomerDocument("customerId", "anyName", "any@email.com", 1001, Customer.Status.ENABLED)

        when: "mocked customerPort is called for finding customer by id"
        FindCustomerByIdPortRequest findByIdPortRequest = FindCustomerByIdPortRequest.create(savedCustomer.getId())
        customerPortMock.find(findByIdPortRequest) >> Mono.just(savedCustomer)

        and: "customerServiceImpl is called for finding customer by id"
        FindCustomerByIdVO request = FindCustomerByIdVO.create(savedCustomer.getId())
        Customer response = customerService.find(request).block()

        then:
        response != null
        response.getId() == savedCustomer.getId()
        response.getName() == savedCustomer.getName()
        response.getEmail() == savedCustomer.getEmail()
        response.getAge() == savedCustomer.getAge()
        response.getStatus() == savedCustomer.getStatus()
    }

    def "should find customers" (String name, String email, Integer ageMin, Integer ageMax){
        given:
        CustomerPortResponse savedCustomer = createCustomerDocument("customerId", "anyName", "any@email.com", 1001, Customer.Status.ENABLED)
        Collection<CustomerPortResponse> savedCustomers = [savedCustomer]

        when: "mocked customerPort is called for finding customers"
        FindCustomersPortRequest findPortRequest = FindCustomersPortRequest.newInstance().withNameRegex(name).withEmailRegex(email).withAgeMin(ageMin).withAgeMax(ageMax).build()
        customerPortMock.find(findPortRequest) >> Flux.fromIterable(savedCustomers)

        and: "customerServiceImpl is called for finding customer by id"
        FindCustomersVO request = FindCustomersVO.newInstance().withNameRegex(name).withEmailRegex(email).withAgeMin(ageMin).withAgeMax(ageMax).build()
        List<Customer> response = customerService.find(request).collectList().block()

        then:
        response.size() == 1
        Customer customer = response.iterator().next()

        customer != null
        customer.getId() == savedCustomer.getId()
        customer.getName() == savedCustomer.getName()
        customer.getEmail() == savedCustomer.getEmail()
        customer.getAge() == savedCustomer.getAge()
        customer.getStatus() == savedCustomer.getStatus()

        where:
        name   | email   | ageMin | ageMax
        null   | null    | null   | null
        "name" | "email" | 10     | 15
    }

}