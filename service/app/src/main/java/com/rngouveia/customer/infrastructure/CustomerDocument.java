package com.rngouveia.customer.infrastructure;

import com.rngouveia.customer.application.port.dto.response.CustomerPortResponse;
import com.rngouveia.customer.domain.Customer;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
public class CustomerDocument implements CustomerPortResponse {

    @Id
    private String id;
    private String name;
    private Integer age;
    private String email;
    private Customer.Status status;

    @Override
    public Customer.Status getStatus() {
        return status;
    }

    public void setStatus(Customer.Status status) {
        this.status = status;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
