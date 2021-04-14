package com.rngouveia.client.registration.api;

import java.util.Optional;

public class UpdateClientApiRequest {
    private String name;
    private Integer age;
    private String email;

    private UpdateClientApiRequest(){}

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public static UpdateClientApiRequest.Builder newInstance(){
        return new UpdateClientApiRequest.Builder();
    }

    public static class Builder {
        private String name;
        private Integer age;
        private String email;

        public UpdateClientApiRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public UpdateClientApiRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public UpdateClientApiRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public UpdateClientApiRequest build(){
            UpdateClientApiRequest client = new UpdateClientApiRequest();
            client.name = name;
            client.age = age;
            client.email = email;
            return client;
        }
    }
}
