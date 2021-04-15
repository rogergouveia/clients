package com.rngouveia.customer.application.port.dto.request;

public class DisableCustomerPortRequest {
    private final String id;

    private DisableCustomerPortRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static DisableCustomerPortRequest create(String id){
        return new DisableCustomerPortRequest(id);
    }

}
