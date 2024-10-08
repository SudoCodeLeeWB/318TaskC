package JOME.OrderService.infrastructure.external.rest;


// A REST API Client to Payment Service ( External 3rd Party service )
// External socket

import JOME.OrderService.domain.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestPaymentClient {

    private final RestTemplate restTemplate;


    // Simplify : the address to the External payment service
    private String paymentServerAddress = "";


    @Autowired
    public RestPaymentClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // just a mock , it will return HTTP response, but for now, we will just read the result from it, and return the boolean
    // instead of payment Details ( for matching the payment details )

    public boolean processPayment( Customer customer ){

        return true;

    }


}
