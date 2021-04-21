package com.rngouveia.customer.integration

import com.rngouveia.customer.Application
import com.rngouveia.customer.application.port.dto.request.CreateCustomerPortRequest
import com.rngouveia.customer.application.port.dto.request.FindCustomerByIdPortRequest
import com.rngouveia.customer.application.port.dto.request.FindCustomersPortRequest
import com.rngouveia.customer.application.port.dto.request.UpdateCustomerPortRequest
import com.rngouveia.customer.application.port.dto.response.CustomerPortResponse
import com.rngouveia.customer.domain.Customer
import com.rngouveia.customer.domain.Customer.Status
import com.rngouveia.customer.infrastructure.CustomerAdapter
import com.rngouveia.customer.infrastructure.CustomerDocument
import com.rngouveia.customer.infrastructure.CustomerRepository
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import spock.lang.Specification

import static com.rngouveia.customer.helper.factory.CustomerDocumentFactory.create
import static org.apache.commons.lang3.StringUtils.isNotBlank

@SpringBootTest(classes = [Application.class])
@ExtendWith(SpringExtension.class)
class CustomerAdapterTest extends Specification {
    @Autowired
    private CustomerRepository mongoRepository

    @Autowired
    private CustomerAdapter adapter

    private CustomerDocument document

    private static CustomerDocument documentJoao
    private static CustomerDocument documentMaria

    def setupSpec() {
        documentJoao = create("id1", "João da Silva", "joaozin@gmail.com", 20, Status.ENABLED)
        documentMaria = create("id2", "Maria de Jesus", "maria@email.com", 50, Status.DISABLED)
    }

    def setup(){
        mongoRepository.deleteAll().block()
        document = create("customerId", "name", "email", 10, Status.ENABLED)
    }

    def cleanup() {
        mongoRepository.deleteAll().block()
    }

    def "Should create customer"(){
        given:
        CreateCustomerPortRequest request = CreateCustomerPortRequest.newInstance().withName("name").withEmail("email").withAge(66).withStatus(Customer.Status.ENABLED).build()

        when:
        CustomerPortResponse response = adapter.create(request).block()

        then:
        response != null
        isNotBlank(response.getId())
        response.getName() == request.getName()
        response.getEmail() == request.getEmail()
        response.getAge() == request.getAge()
        response.getStatus() == request.getStatus()
    }

    def "Should update customer"(String newName, String newEmail, Integer newAge, Status newStatus){
        given: "the document saved in the db"
        mongoRepository.save(document).block()

        and:
        UpdateCustomerPortRequest request = UpdateCustomerPortRequest.newInstance().withId(document.getId()).withName(newName).withEmail(newEmail).withAge(newAge).withStatus(newStatus).build()

        when:
        CustomerPortResponse response = adapter.update(request).block()

        then:
        response != null
        response.getId() == request.getId()
        response.getName() == request.getName().orElse(document.getName())
        response.getEmail() == request.getEmail().orElse(document.getEmail())
        response.getAge() == request.getAge().orElse(document.getAge())
        response.getStatus() == request.getStatus().orElse(document.getStatus())

        where:
        newName       | newEmail           | newAge | newStatus
        null          | null               | null   | null
        "anotherName" | null               | null   | null
        null          | "another@mail.com" | 999    | Status.DISABLED
        null          | null               | null   | Status.ENABLED
        "thisName"    | "another@mail.com" | 88     | Status.ENABLED
    }

    def "Should find customer by id"(){
        given: "the document saved in the db"
        mongoRepository.save(document).block()

        and:
        FindCustomerByIdPortRequest request = FindCustomerByIdPortRequest.create(document.getId())

        when:
        CustomerPortResponse response = adapter.find(request).block()

        then:
        response != null
        response.getId() == document.getId()
        response.getName() == document.getName()
        response.getEmail() == document.getEmail()
        response.getAge() == document.getAge()
        response.getStatus() == document.getStatus()
    }

    def "Should find all customers"(String nameRegex, String emailRegex, Integer ageMin, Integer ageMax, Collection<CustomerDocument> expectedResponse){
        given: "the documents saved in the db"
        Collection<CustomerDocument> documents = [documentJoao, documentMaria]
        mongoRepository.saveAll(documents).collectList().block()

        and:
        FindCustomersPortRequest request = FindCustomersPortRequest.newInstance().withNameRegex(nameRegex).withEmailRegex(emailRegex).withAgeMin(ageMin).withAgeMax(ageMax).build()

        when:
        List<CustomerPortResponse> response = adapter.find(request).collectList().block()

        then:
        response != null
        response.containsAll(expectedResponse)

        where:
        nameRegex | emailRegex | ageMin | ageMax || expectedResponse
        null      | null       | null   | null   || [documentJoao, documentMaria]
        "SILVA"   | null       | null   | null   || [documentJoao]
        "silva"   | null       | null   | null   || [documentJoao]
        "Jesus"   | null       | null   | null   || [documentMaria]
        null      | "yahoo"    | null   | null   || []
        null      | "mail"     | null   | null   || [documentJoao, documentMaria]
        null      | "gmail"    | null   | null   || [documentJoao]
        null      | "mail"     | null   | 18     || []
        null      | null       | null   | 20     || [documentJoao]
        null      | null       | 20     | 50     || [documentJoao, documentMaria]
        null      | null       | 51     | null   || []

    }
}