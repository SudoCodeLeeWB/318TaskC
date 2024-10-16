package JOME.OrderService.infrastructure.external.messaging;

import JOME.OrderService.application.OrderService;
import JOME.shared_events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@KafkaListener(topics = "UserChange" , groupId = "order-group")
public class KafkaConsumerServiceUser {

    // Call Application layer ( dataflow sequence )
    private final OrderService orderService;

    @Autowired
    public KafkaConsumerServiceUser(OrderService orderService) {
        this.orderService = orderService;
    }



    // Consumer for ProductChange topic
    @KafkaHandler
    public void consumeAddNewUserEvent(UserAddEventShared event){

        // DEBUG :
        System.out.println("Received UserAdd event: " + event);
        orderService.handleCustomerCreated(event);

    }


    @KafkaHandler
    public void consumeUpdateProductStockEvent(UserAddressModifyEventShared event){

        // DEBUG :
        System.out.println("Received UserAddressUpdate event: " + event);
        orderService.handleCustomerUpdated(event);

    }




    // Consumer for CustomerChange topic



}
