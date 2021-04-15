package com.rngouveia.customer.application.port.dto.response;

import com.rngouveia.customer.domain.Customer;

public interface CustomerPortResponse {
    String getId();
    String getName();
    Integer getAge();
    String getEmail();
    Customer.Status getStatus();
}
