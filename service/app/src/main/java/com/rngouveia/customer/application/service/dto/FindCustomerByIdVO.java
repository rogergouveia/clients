package com.rngouveia.customer.application.service.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FindCustomerByIdVO that = (FindCustomerByIdVO) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
