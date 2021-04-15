package com.rngouveia.customer.application.service.dto;

public class DisableCustomerVO {
    private final String id;

    private DisableCustomerVO(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static DisableCustomerVO create(String id){
        return new DisableCustomerVO(id);
    }
}
