package com.rngouveia.client.registration.application.service.dto;

import javax.validation.constraints.NotBlank;

public class FindClientByIdVO {
    @NotBlank
    private String id;

    private FindClientByIdVO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FindClientByIdVO create(String id){
        return new FindClientByIdVO(id);
    }
}
