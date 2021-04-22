package com.rngouveia.customer.application.service.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotBlank;

public class FindCustomerByIdServiceRequest {
    @NotBlank
    private String id;

    private FindCustomerByIdServiceRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static FindCustomerByIdServiceRequest create(String id){
        return new FindCustomerByIdServiceRequest(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FindCustomerByIdServiceRequest that = (FindCustomerByIdServiceRequest) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
