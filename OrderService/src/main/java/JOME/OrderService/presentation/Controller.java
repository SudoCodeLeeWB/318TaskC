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








}
