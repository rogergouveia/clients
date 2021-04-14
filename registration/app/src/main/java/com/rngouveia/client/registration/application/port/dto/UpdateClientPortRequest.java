package com.rngouveia.client.registration.application.port.dto;

import java.util.Optional;

public class UpdateClientPortRequest {
    private String id;
    private String name;
    private Integer age;
    private String email;

    private UpdateClientPortRequest(){}

    public String getId() {
        return id;
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

        public UpdateClientPortRequest build(){
            UpdateClientPortRequest client = new UpdateClientPortRequest();
            client.id = id;
            client.name = name;
            client.age = age;
            client.email = email;
            return client;
        }
    }
}
