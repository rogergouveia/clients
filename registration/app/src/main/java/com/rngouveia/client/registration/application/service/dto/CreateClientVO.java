package com.rngouveia.client.registration.application.service.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateClientVO {
    @NotBlank
    private String name;
    @NotNull
    private Integer age;
    @NotBlank
    private String email;

    private CreateClientVO(){}

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

        public CreateClientVO.Builder withName(String name){
            this.name = name;
            return this;
        }

        public CreateClientVO.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public CreateClientVO.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public CreateClientVO build(){
            CreateClientVO client = new CreateClientVO();
            client.name = name;
            client.age = age;
            client.email = email;
            return client;
        }
    }
}
