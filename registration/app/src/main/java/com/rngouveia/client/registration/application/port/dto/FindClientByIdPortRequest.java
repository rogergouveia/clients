package com.rngouveia.client.registration.application.port.dto;

import javax.validation.constraints.NotBlank;

public class FindClientByIdPortRequest {
    @NotBlank
    private String id;

    private FindClientByIdPortRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FindClientByIdPortRequest create(String id){
        return new FindClientByIdPortRequest(id);
    }
}
