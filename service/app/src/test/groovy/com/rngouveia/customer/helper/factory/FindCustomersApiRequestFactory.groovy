package com.rngouveia.customer.helper.factory

import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest

class FindCustomersApiRequestFactory {

    static FindCustomersApiRequest create(String nameRegex, String emailRegex, Integer ageMin, Integer ageMax){
        FindCustomersApiRequest request = new FindCustomersApiRequest()
        request.setNameRegex(nameRegex)
        request.setEmailRegex(emailRegex)
        request.setAgeMin(ageMin)
        request.setAgeMax(ageMax)
        return request
    }
}
