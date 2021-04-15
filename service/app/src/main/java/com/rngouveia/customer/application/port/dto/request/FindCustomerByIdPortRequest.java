package com.rngouveia.customer.application.port.dto.request;

import javax.validation.constraints.NotBlank;

public class FindCustomerByIdPortRequest {
    @NotBlank
    private String id;

    private FindCustomerByIdPortRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FindCustomerByIdPortRequest create(String id){
        return new FindCustomerByIdPortRequest(id);
    }
}
