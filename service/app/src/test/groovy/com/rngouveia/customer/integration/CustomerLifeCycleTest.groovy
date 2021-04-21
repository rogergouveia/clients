package com.rngouveia.customer.integration

import com.rngouveia.customer.Application
import com.rngouveia.customer.api.dto.request.CreateCustomerApiRequest
import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest
import com.rngouveia.customer.api.dto.request.UpdateCustomerApiRequest
import com.rngouveia.customer.api.dto.response.CustomerApiResponse
import com.rngouveia.customer.domain.Customer
import com.rngouveia.customer.helper.CustomerInternalApiHelper
import com.rngouveia.customer.helper.factory.FindCustomersApiRequestFactory
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
class CustomerLifeCycleTest extends Specification implements CustomerInternalApiHelper {
    @Autowired
    private CustomerRepository mongoRepository

    @Shared
    private CustomerApiResponse customerApiResponse

    def setup() {
        resetWebTestClient()
    }

    def "Should create new customer"(){
        given: "there are no customers on the db"
        mongoRepository.deleteAll().block()

        and:"user will inform age 32, name 'João da Silva' and email 'joaozinho@whatever.com'"
        Integer age = 32
        String name = "João da Silva"
        String email = "joaozinho@whatever.com"

        when:"user request to create customer"
        CreateCustomerApiRequest apiRrequest = CreateCustomerApiRequest.newInstance().withName(name).withAge(age).withEmail(email).build()
        EntityExchangeResult<CustomerApiResponse> response = createCustomer(apiRrequest).expectBody(CustomerApiResponse.class).returnResult()
        customerApiResponse = response.getResponseBody()

        then:"should have response status 'created'"
        response.getStatus() == HttpStatus.CREATED

        and:"should have an id, the same data as informed, and status ENABLED"
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

        and:"should be the same created customer"
        response.getResponseBody() == customerApiResponse

    }

    def "should find original customer by filter"(String nameRegex, String emailRegex, Integer ageMin, Integer ageMax, Collection<CustomerApiResponse> responseBody){
        when: "user request to find"
        FindCustomersApiRequest request = FindCustomersApiRequestFactory.create(nameRegex, emailRegex, ageMin, ageMax)
        EntityExchangeResult<List<CustomerApiResponse>> response = findCustomers(request).expectBodyList(CustomerApiResponse.class).returnResult()

        then:"should have response status 'ok'"
        response.getStatus() == HttpStatus.OK

        and:"should find customer based on filters"
        response.getResponseBody() == responseBody

        where:
        nameRegex   | emailRegex   | ageMin | ageMax || responseBody
        null        | null         | null   | null   || [customerApiResponse]
        "silva"     | null         | null   | null   || [customerApiResponse]
        "SILVA"     | null         | null   | null   || [customerApiResponse]
        "Paiva"     | null         | null   | null   || []
        null        | "joaozin"    | null   | null   || [customerApiResponse]
        null        | null         | 30     | null   || [customerApiResponse]
        null        | null         | 33     | null   || []
        null        | null         | 32     | 34     || [customerApiResponse]
        "Paiva"     | null         | 32     | 34     || []
        null        | "paivinha"   | 32     | 34     || []
        null        | null         | 32     | 31     || []
    }

    def "should update customer"(){
        given: "user will inform the created customer's id, name 'Juca da Silva', email 'garototrakinas@gmail.com' and age 22"
        String id = customerApiResponse.getId()
        String newName = "Juca da Silva"
        String newEmail = "garototrakinas@gmail.com"
        Integer newAge = 22

        when: "user requests to update customer"
        UpdateCustomerApiRequest apiRequest = UpdateCustomerApiRequest.newInstance().withName(newName).withEmail(newEmail).withAge(newAge).build()
        EntityExchangeResult<CustomerApiResponse> response = updateCustomer(id, apiRequest).expectBody(CustomerApiResponse.class).returnResult()
        customerApiResponse = response.getResponseBody()


        then: "should have response status 'ok'"
        response.getStatus() == HttpStatus.OK

        and:"should have the same id of the created customer, the same data as informed and status ENABLED"
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

        and:"should be the same updated customer"
        response.getResponseBody() == customerApiResponse
    }

    def "should disable customer"(){
        given: "user will inform the created customer's id"
        String id = customerApiResponse.getId()

        when:"user requests to disable customer"
        EntityExchangeResult<CustomerApiResponse> response = disableCustomer(id).expectBody(CustomerApiResponse.class).returnResult()

        then: "should have response status 'ok'"
        response.getStatus() == HttpStatus.OK

        and: "should have the same data as the updated customer, and the status DISABLED"
        response.getResponseBody().getId() == customerApiResponse.getId()
        response.getResponseBody().getName() == customerApiResponse.getName()
        response.getResponseBody().getEmail() == customerApiResponse.getEmail()
        response.getResponseBody().getAge() == customerApiResponse.getAge()
        response.getResponseBody().getStatus() == Customer.Status.DISABLED
    }
}