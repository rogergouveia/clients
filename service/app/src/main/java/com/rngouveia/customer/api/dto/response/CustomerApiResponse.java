package com.rngouveia.customer.api.dto.response;

import com.rngouveia.customer.domain.Customer;

public class CustomerApiResponse {
    private String id;
    private String name;
    private Integer age;
    private String email;
    private Customer.Status status;

    private CustomerApiResponse(){

    }

    public Customer.Status getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
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

        public CustomerApiResponse build(){
            CustomerApiResponse customer = new CustomerApiResponse();
            customer.id = id;
            customer.name = name;
            customer.age = age;
            customer.email = email;
            customer.status = status;
            return customer;
        }
    }
}
