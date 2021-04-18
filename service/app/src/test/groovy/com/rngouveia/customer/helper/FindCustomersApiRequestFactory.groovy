package com.rngouveia.customer.helper

import com.rngouveia.customer.api.dto.request.FindCustomersApiRequest

class FindCustomersApiRequestFactory {

    public static FindCustomersApiRequest create(String nameRegex, String emailRegex, Integer ageMin, Integer ageMax){
        FindCustomersApiRequest request = new FindCustomersApiRequest()
        request.setNameRegex(nameRegex)
        request.setEmailRegex(emailRegex)
        request.setAgeMin(ageMin)
        request.setAgeMax(ageMax)
        return request
    }
}
