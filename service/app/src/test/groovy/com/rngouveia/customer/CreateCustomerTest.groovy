package com.rngouveia.customer


import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest
import com.rngouveia.customer.api.dto.response.CustomerApiResponse
import com.rngouveia.customer.domain.Customer
import com.rngouveia.customer.helper.CustomerInternalApiHelper
import com.rngouveia.customer.infrastructure.CustomerRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.EntityExchangeResult
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@SpringBootTest(classes = [Application.class])
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@Stepwise
class CreateCustomerTest extends Specification implements CustomerInternalApiHelper {
    @Autowired
    private CustomerRepository mongoRepository

    @Shared
    private CustomerApiResponse customerApiResponse

    def setup() {
        resetWebTestClient()
    }

    def "Should create new customer"(){
        given:"user will inform age 32"
        Integer age = 32

        and:"user will inform name 'João da Silva'"
        String name = "João da Silva"

        and:"user will inform email 'joaozinho@whatever.com'"
        String email = "joaozinho@whatever.com"

        when:"user request to create customer"
        CreateCustomerApiRequest apiRrequest = CreateCustomerApiRequest.newInstance().withName(name).withAge(age).withEmail(email).build()
        EntityExchangeResult<CustomerApiResponse> response = createCustomer(apiRrequest).expectBody(CustomerApiResponse.class).returnResult()
        customerApiResponse = response.getResponseBody()

        then:"should have response status 'created'"
        response.getStatus() == HttpStatus.CREATED

        and:""
        customerApiResponse.getId() != null && !customerApiResponse.getId().isBlank()
        customerApiResponse.getAge() == age
        customerApiResponse.getName() == name
        customerApiResponse.getEmail() == email
        customerApiResponse.getStatus() == Customer.Status.ENABLED
    }

    def "should find customer by id"(){

        given: "user will inform the created customer's id"
        String id = customerApiResponse.getId()

        when: "user request to find"
        EntityExchangeResult<CustomerApiResponse> response = findByIdCustomer(id).expectBody(CustomerApiResponse.class).returnResult()

        then:"should have response status 'ok'"
        response.getStatus() == HttpStatus.OK

        and:""
        response.getResponseBody() == customerApiResponse

    }

    def "should update customer"(){
        given: "user will inform the created customer's id"
        String id = customerApiResponse.getId()

        and:"user will inform new name as 'Juca da Silva'"
        String newName = "Juca da Silva"

        and:"user will inform new email as 'garototrakinas@gmail.com'"
        String newEmail = "garototrakinas@gmail.com"

        and:"user will inform new age as 22"
        Integer newAge = 22

        when: "user requests to update customer"
        UpdateCustomerApiRequest apiRequest = UpdateCustomerApiRequest.newInstance().withName(newName).withEmail(newEmail).withAge(newAge).build()
        EntityExchangeResult<CustomerApiResponse> response = updateCustomer(id, apiRequest).expectBody(CustomerApiResponse.class).returnResult()
        customerApiResponse = response.getResponseBody()


        then: "should have response status 'ok'"
        response.getStatus() == HttpStatus.OK

        and:""
        customerApiResponse.getId() == id
        customerApiResponse.getAge() == newAge
        customerApiResponse.getName() == newName
        customerApiResponse.getEmail() == newEmail
        customerApiResponse.getStatus() == Customer.Status.ENABLED
    }

    def "should find customer by id after update"(){
        given: "user will inform the created customer's id"
        String id = customerApiResponse.getId()

        when: "user request to find"
        EntityExchangeResult<CustomerApiResponse> response = findByIdCustomer(id).expectBody(CustomerApiResponse.class).returnResult()

        then:"should have response status 'ok'"
        response.getStatus() == HttpStatus.OK

        and:""
        response.getResponseBody() == customerApiResponse
    }

    def "should disable customer"(){
        given: "user will inform the created customer's id"
        String id = customerApiResponse.getId()

        when:"user requests to disable customer"
        EntityExchangeResult<CustomerApiResponse> response = disableCustomer(id).expectBody(CustomerApiResponse.class).returnResult()

        then: "should have response status 'ok'"
        response.getStatus() == HttpStatus.OK

        and: ""
        response.getResponseBody().getId() == customerApiResponse.getId()
        response.getResponseBody().getName() == customerApiResponse.getName()
        response.getResponseBody().getEmail() == customerApiResponse.getEmail()
        response.getResponseBody().getAge() == customerApiResponse.getAge()
        response.getResponseBody().getStatus() == Customer.Status.DISABLED
    }
}