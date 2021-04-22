package com.rngouveia.customer.application.service.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

public class FindCustomersServiceRequest {
    private String nameRegex;
    @PositiveOrZero
    private Integer ageMin;
    @PositiveOrZero
    private Integer ageMax;
    private String emailRegex;

    private FindCustomersServiceRequest() {
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

        public FindCustomersServiceRequest build(){
            FindCustomersServiceRequest vo = new FindCustomersServiceRequest();
            vo.nameRegex = nameRegex;
            vo.ageMin = ageMin;
            vo.ageMax = ageMax;
            vo.emailRegex = emailRegex;
            return vo;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FindCustomersServiceRequest that = (FindCustomersServiceRequest) o;

        return new EqualsBuilder().append(nameRegex, that.nameRegex).append(ageMin, that.ageMin).append(ageMax, that.ageMax).append(emailRegex, that.emailRegex).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(nameRegex).append(ageMin).append(ageMax).append(emailRegex).toHashCode();
    }
}
