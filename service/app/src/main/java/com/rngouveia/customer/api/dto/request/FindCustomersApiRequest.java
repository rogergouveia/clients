package com.rngouveia.customer.api.dto.request;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

public class FindCustomersApiRequest {
    private String nameRegex;
    @PositiveOrZero
    private Integer ageMin;
    @PositiveOrZero
    private Integer ageMax;
    private String emailRegex;

    private FindCustomersApiRequest() {
    }

    public Optional<String> getNameRegex() {
        return Optional.ofNullable(nameRegex);
    }

    public Optional<Integer> getAgeMin() {
        return Optional.ofNullable(ageMin);
    }

    public Optional<Integer> getAgeMax() {
        return Optional.ofNullable(ageMax);
    }

    public Optional<String> getEmailRegex() {
        return Optional.ofNullable(emailRegex);
    }

    public static FindCustomersApiRequest.Builder newInstance(){
        return new FindCustomersApiRequest.Builder();
    }

    public static class Builder{
        private String nameRegex;
        private Integer ageMin;
        private Integer ageMax;
        private String emailRegex;

        public FindCustomersApiRequest.Builder withNameRegex(String nameRegex){
            this.nameRegex=nameRegex;
            return this;
        }

        public FindCustomersApiRequest.Builder withAgeMin(Integer ageMin){
            this.ageMin=ageMin;
            return this;
        }

        public FindCustomersApiRequest.Builder withAgeMax(Integer ageMax){
            this.ageMax=ageMax;
            return this;
        }

        public FindCustomersApiRequest.Builder withEmailRegex(String emailRegex){
            this.emailRegex=emailRegex;
            return this;
        }

        public FindCustomersApiRequest build(){
            FindCustomersApiRequest vo = new FindCustomersApiRequest();
            vo.nameRegex = nameRegex;
            vo.ageMin = ageMin;
            vo.ageMax = ageMax;
            vo.emailRegex = emailRegex;
            return vo;
        }
    }
}
