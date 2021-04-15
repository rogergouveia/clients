package com.rngouveia.customer.application.port.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateCustomerPortRequest {
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    @NotBlank
    private String email;

    private CreateCustomerPortRequest(){}

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
        private String name;
        private Integer age;
        private String email;

        public CreateCustomerPortRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public CreateCustomerPortRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public CreateCustomerPortRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public CreateCustomerPortRequest build(){
            CreateCustomerPortRequest customer = new CreateCustomerPortRequest();
            customer.name = name;
            customer.age = age;
            customer.email = email;
            return customer;
        }
    }
}
