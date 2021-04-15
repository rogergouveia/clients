package com.rngouveia.customer.api.dto.request;

public class CreateCustomerApiRequest {
    private String name;
    private Integer age;
    private String email;

    private CreateCustomerApiRequest(){}

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

        public CreateCustomerApiRequest.Builder withName(String name){
            this.name = name;
            return this;
        }

        public CreateCustomerApiRequest.Builder withAge(Integer age){
            this.age = age;
            return this;
        }

        public CreateCustomerApiRequest.Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public CreateCustomerApiRequest build(){
            CreateCustomerApiRequest customer = new CreateCustomerApiRequest();
            customer.name = name;
            customer.age = age;
            customer.email = email;
            return customer;
        }
    }
}
