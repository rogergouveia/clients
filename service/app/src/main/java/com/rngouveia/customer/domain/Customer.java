package com.rngouveia.customer.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class Customer {
    public enum Status {ENABLED, DISABLED}

    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotNull
    @PositiveOrZero
    private Integer age;
    @NotBlank
    @Email
    private String email;

    @NotNull
    private Status status;

    private Customer(){}

    public Status getStatus() {
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
        private Status status;

        public Builder withStatus(Status status){
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

        public Customer build(){
            Customer customer = new Customer();
            customer.id = id;
            customer.name = name;
            customer.age = age;
            customer.email = email;
            customer.status = status;
            return customer;
        }
    }
}
