package com.rngouveia.client.registration.api;

public class CreateClientApiRequest {
    private String name;
    private Integer age;
    private String email;

    private CreateClientApiRequest(){}

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public static class Builder {
        private String name;
        private Integer age;
        private String email;

        public CreateClientApiRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public CreateClientApiRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public CreateClientApiRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public CreateClientApiRequest build(){
            CreateClientApiRequest client = new CreateClientApiRequest();
            client.name = name;
            client.age = age;
            client.email = email;
            return client;
        }
    }
}
