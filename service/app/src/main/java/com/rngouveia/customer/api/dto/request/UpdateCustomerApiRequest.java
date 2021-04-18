package com.rngouveia.customer.api.dto.request;

import java.util.Optional;

public class UpdateCustomerApiRequest {
    private String name;
    private Integer age;
    private String email;

    private UpdateCustomerApiRequest(){}

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public static UpdateCustomerApiRequest.Builder newInstance(){
        return new UpdateCustomerApiRequest.Builder();
    }

    public static class Builder {
        private String name;
        private Integer age;
        private String email;

        public UpdateCustomerApiRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public UpdateCustomerApiRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public UpdateCustomerApiRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public UpdateCustomerApiRequest build(){
            UpdateCustomerApiRequest customer = new UpdateCustomerApiRequest();
            customer.name = name;
            customer.age = age;
            customer.email = email;
            return customer;
        }
    }
}
