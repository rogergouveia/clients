package com.rngouveia.client.registration.application.port.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateClientPortRequest {
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    @NotBlank
    private String email;

    private CreateClientPortRequest(){}

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

        public CreateClientPortRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public CreateClientPortRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public CreateClientPortRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public CreateClientPortRequest build(){
            CreateClientPortRequest client = new CreateClientPortRequest();
            client.name = name;
            client.age = age;
            client.email = email;
            return client;
        }
    }
}
