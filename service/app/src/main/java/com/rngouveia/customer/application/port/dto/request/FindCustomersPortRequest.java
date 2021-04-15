package com.rngouveia.customer.application.port.dto.request;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

public class FindCustomersPortRequest {
    private String nameRegex;
    @PositiveOrZero
    private Integer ageMin;
    @PositiveOrZero
    private Integer ageMax;
    private String emailRegex;

    private FindCustomersPortRequest() {
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

    public static FindCustomersPortRequest.Builder newInstance(){
        return new FindCustomersPortRequest.Builder();
    }

    public static class Builder{
        private String nameRegex;
        private Integer ageMin;
        private Integer ageMax;
        private String emailRegex;

        public FindCustomersPortRequest.Builder withNameRegex(String nameRegex){
            this.nameRegex=nameRegex;
            return this;
        }

        public FindCustomersPortRequest.Builder withAgeMin(Integer ageMin){
            this.ageMin=ageMin;
            return this;
        }

        public FindCustomersPortRequest.Builder withAgeMax(Integer ageMax){
            this.ageMax=ageMax;
            return this;
        }

        public FindCustomersPortRequest.Builder withEmailRegex(String emailRegex){
            this.emailRegex=emailRegex;
            return this;
        }

        public FindCustomersPortRequest build(){
            FindCustomersPortRequest vo = new FindCustomersPortRequest();
            vo.nameRegex = nameRegex;
            vo.ageMin = ageMin;
            vo.ageMax = ageMax;
            vo.emailRegex = emailRegex;
            return vo;
        }
    }
}
