package com.rngouveia.client.registration.application.port.dto;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

public class FindClientsPortRequest {
    private String nameRegex;
    @PositiveOrZero
    private Integer ageMin;
    @PositiveOrZero
    private Integer ageMax;
    private String emailRegex;

    private FindClientsPortRequest() {
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

    public static FindClientsPortRequest.Builder newInstance(){
        return new FindClientsPortRequest.Builder();
    }

    public static class Builder{
        private String nameRegex;
        private Integer ageMin;
        private Integer ageMax;
        private String emailRegex;

        public FindClientsPortRequest.Builder withNameRegex(String nameRegex){
            this.nameRegex=nameRegex;
            return this;
        }

        public FindClientsPortRequest.Builder withAgeMin(Integer ageMin){
            this.ageMin=ageMin;
            return this;
        }

        public FindClientsPortRequest.Builder withAgeMax(Integer ageMax){
            this.ageMax=ageMax;
            return this;
        }

        public FindClientsPortRequest.Builder withEmailRegex(String emailRegex){
            this.emailRegex=emailRegex;
            return this;
        }

        public FindClientsPortRequest build(){
            FindClientsPortRequest vo = new FindClientsPortRequest();
            vo.nameRegex = nameRegex;
            vo.ageMin = ageMin;
            vo.ageMax = ageMax;
            vo.emailRegex = emailRegex;
            return vo;
        }
    }
}
