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

    public void setNameRegex(String nameRegex) {
        this.nameRegex = nameRegex;
    }

    public void setAgeMin(Integer ageMin) {
        this.ageMin = ageMin;
    }

    public void setAgeMax(Integer ageMax) {
        this.ageMax = ageMax;
    }

    public void setEmailRegex(String emailRegex) {
        this.emailRegex = emailRegex;
    }
}
