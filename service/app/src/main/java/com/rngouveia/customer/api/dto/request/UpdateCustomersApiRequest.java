package com.rngouveia.customer.api.dto.request;

import java.util.Optional;

public class UpdateCustomersApiRequest {
    private String name;
    private Integer age;
    private String email;

    private UpdateCustomersApiRequest(){}

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public static UpdateCustomersApiRequest.Builder newInstance(){
        return new UpdateCustomersApiRequest.Builder();
    }

    public static class Builder {
        private String name;
        private Integer age;
        private String email;

        public UpdateCustomersApiRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public UpdateCustomersApiRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public UpdateCustomersApiRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public UpdateCustomersApiRequest build(){
            UpdateCustomersApiRequest customer = new UpdateCustomersApiRequest();
            customer.name = name;
            customer.age = age;
            customer.email = email;
            return customer;
        }
    }
}
