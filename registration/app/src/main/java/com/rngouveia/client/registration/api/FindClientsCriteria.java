package com.rngouveia.client.registration.api;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

public class FindClientsCriteria {
    private String nameRegex;
    @PositiveOrZero
    private Integer ageMin;
    @PositiveOrZero
    private Integer ageMax;
    private String emailRegex;

    private FindClientsCriteria() {
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

    public static FindClientsCriteria.Builder newInstance(){
        return new FindClientsCriteria.Builder();
    }

    public static class Builder{
        private String nameRegex;
        private Integer ageMin;
        private Integer ageMax;
        private String emailRegex;

        public FindClientsCriteria.Builder withNameRegex(String nameRegex){
            this.nameRegex=nameRegex;
            return this;
        }

        public FindClientsCriteria.Builder withAgeMin(Integer ageMin){
            this.ageMin=ageMin;
            return this;
        }

        public FindClientsCriteria.Builder withAgeMax(Integer ageMax){
            this.ageMax=ageMax;
            return this;
        }

        public FindClientsCriteria.Builder withEmailRegex(String emailRegex){
            this.emailRegex=emailRegex;
            return this;
        }

        public FindClientsCriteria build(){
            FindClientsCriteria vo = new FindClientsCriteria();
            vo.nameRegex = nameRegex;
            vo.ageMin = ageMin;
            vo.ageMax = ageMax;
            vo.emailRegex = emailRegex;
            return vo;
        }
    }
}
