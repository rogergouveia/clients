package com.rngouveia.customer.application.port.dto.request;

import com.rngouveia.customer.domain.Customer;

import java.util.Optional;

public class UpdateCustomerPortRequest {
    private String id;
    private String name;
    private Integer age;
    private String email;
    private Customer.Status status;

    private UpdateCustomerPortRequest(){}

    public String getId() {
        return id;
    }

    public Optional<Customer.Status> getStatus() {
        return Optional.ofNullable(status);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public static Builder newInstance(){
        return new Builder();
    }

    public static class Builder {
        private String id;
        private String name;
        private Integer age;
        private String email;
        private Customer.Status status;

        public Builder withStatus(Customer.Status status){
            this.status = status;
            return this;
        }

        public Builder withId(String id){
            this.id = id;
            return this;
        }

        public Builder withName(String name){
            this.name = name;
            return this;
        }

        public Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public UpdateCustomerPortRequest build(){
            UpdateCustomerPortRequest customer = new UpdateCustomerPortRequest();
            customer.id = id;
            customer.name = name;
            customer.age = age;
            customer.email = email;
            customer.status = status;
            return customer;
        }
    }
}
