package com.rngouveia.customer.application.service.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DisableCustomerVO that = (DisableCustomerVO) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
