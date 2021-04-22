package com.rngouveia.customer.application.service.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class CreateCustomerServiceRequest {
    @NotBlank
    private String name;
    @NotNull
    @PositiveOrZero
    private Integer age;
    @NotBlank
    @Email
    private String email;

    private CreateCustomerServiceRequest(){}

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

        public CreateCustomerServiceRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public CreateCustomerServiceRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public CreateCustomerServiceRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public CreateCustomerServiceRequest build(){
            CreateCustomerServiceRequest customer = new CreateCustomerServiceRequest();
            customer.name = name;
            customer.age = age;
            customer.email = email;
            return customer;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        CreateCustomerServiceRequest that = (CreateCustomerServiceRequest) o;

        return new EqualsBuilder().append(name, that.name).append(age, that.age).append(email, that.email).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(name).append(age).append(email).toHashCode();
    }
}
