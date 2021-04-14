package com.rngouveia.client.registration.api;

import com.rngouveia.client.registration.domain.Client;

public class ClientApiDto {
    private String id;
    private String name;
    private Integer age;
    private String email;

    private ClientApiDto(){

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

        public ClientApiDto build(){
            ClientApiDto client = new ClientApiDto();
            client.id = id;
            client.name = name;
            client.age = age;
            client.email = email;
            return client;
        }
    }
}
