package com.rngouveia.customer.application.port.dto.request;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FindCustomerByIdPortRequest that = (FindCustomerByIdPortRequest) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
