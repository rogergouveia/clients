package com.rngouveia.customer.application.service.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DisableCustomerServiceRequest {
    private final String id;

    private DisableCustomerServiceRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static DisableCustomerServiceRequest create(String id){
        return new DisableCustomerServiceRequest(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DisableCustomerServiceRequest that = (DisableCustomerServiceRequest) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
