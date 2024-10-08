package JOME.OrderService.presentation;


import JOME.OrderService.application.OrderService;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Controller {


    private final OrderService orderService;


    @Autowired
    public Controller ( OrderService orderService){
        this.orderService = orderService;
    }


    // Add REST Mappings HERE

    // usecase 1 : Add new product to the shoppingCart


    // usecase 2 : add product quantity to the shopping cart


    // usecase 3 : remove product quantity from the shopping cart


    // usecase 4 : place order


    // usecase 5 : Cancel Order









}
