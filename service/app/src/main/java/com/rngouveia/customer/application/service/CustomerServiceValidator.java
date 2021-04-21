package com.rngouveia.customer.application.service;

import com.rngouveia.customer.application.error.ServiceException;
import com.rngouveia.customer.application.service.dto.UpdateCustomerVO;
import com.rngouveia.customer.domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceValidator {

    public void validate(UpdateCustomerVO vo, Customer document) {
        shouldBeUpdatingSomething(vo);
        shouldBeEnabled(vo, document);
    }

    private void shouldBeUpdatingSomething(UpdateCustomerVO vo){
        if (vo.getName().isEmpty() && vo.getEmail().isEmpty() && vo.getAge().isEmpty()){
            throw new ServiceException(String.format("The 'update customer' functionality should try to update AT LEAST ONE FIELD! updateRequest -> %s", vo));
        }
    }

    private void shouldBeEnabled(UpdateCustomerVO vo, Customer customer){
        if (customer.getStatus() != Customer.Status.ENABLED){
            throw new ServiceException(String.format("The 'update customer' functionality must only update ENABLED customers! updateRequest -> %s, customer -> %s", vo, customer));
        }
    }

}
