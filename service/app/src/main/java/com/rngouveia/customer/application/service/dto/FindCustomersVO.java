package com.rngouveia.customer.application.service.dto;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

public class FindCustomersVO {
    private String nameRegex;
    @PositiveOrZero
    private Integer ageMin;
    @PositiveOrZero
    private Integer ageMax;
    private String emailRegex;

    private FindCustomersVO() {
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

    public static Builder newInstance(){
        return new Builder();
    }

    public static class Builder{
        private String nameRegex;
        private Integer ageMin;
        private Integer ageMax;
        private String emailRegex;

        public Builder withNameRegex(String nameRegex){
            this.nameRegex=nameRegex;
            return this;
        }

        public Builder withAgeMin(Integer ageMin){
            this.ageMin=ageMin;
            return this;
        }

        public Builder withAgeMax(Integer ageMax){
            this.ageMax=ageMax;
            return this;
        }

        public Builder withEmailRegex(String emailRegex){
            this.emailRegex=emailRegex;
            return this;
        }

        public FindCustomersVO build(){
            FindCustomersVO vo = new FindCustomersVO();
            vo.nameRegex = nameRegex;
            vo.ageMin = ageMin;
            vo.ageMax = ageMax;
            vo.emailRegex = emailRegex;
            return vo;
        }
    }

}
