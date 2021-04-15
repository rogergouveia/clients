package com.rngouveia.customer.application.service.dto;

import javax.validation.constraints.NotBlank;

public class FindCustomerByIdVO {
    @NotBlank
    private String id;

    private FindCustomerByIdVO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FindCustomerByIdVO create(String id){
        return new FindCustomerByIdVO(id);
    }
}
