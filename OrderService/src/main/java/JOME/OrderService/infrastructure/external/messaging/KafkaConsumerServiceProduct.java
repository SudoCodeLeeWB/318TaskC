package JOME.OrderService.infrastructure.external.messaging;

import JOME.OrderService.application.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import JOME.shared_events.*;

@Service
@KafkaListener(topics = "ProductChange" , groupId = "order-group")
public class KafkaConsumerServiceProduct {


    // Call Application layer ( dataflow sequence )
    private final OrderService orderService;

    @Autowired
    public KafkaConsumerServiceProduct(OrderService orderService) {
        this.orderService = orderService;
    }


    // Kafka listener to consume Messages

    // Strategy : Different Event -> automatically deseralize ( try to match with correct event type )
    // If this not works, consider using event type header


    // Consumer for ProductChange topic
    @KafkaHandler
    public void consumeAddNewProductEvent(AddNewProductEventShared event){

        // DEBUG :
        System.out.println("Received ProductChange event: " + event);
        orderService.handleProductCreated(event);

    }


    @KafkaHandler
    public void consumeUpdateProductStockEvent(UpdateProductStockEventShared event){

        // DEBUG :
        System.out.println("Received ProductChange event: " + event);
        orderService.handleProductUpdated(event);

    }



    @KafkaHandler
    public void consumeProductDeleteEvent(ProductDeleteEventShared event){

        // DEBUG :
        System.out.println("Received ProductChange event: " + event);
        orderService.handleProductDeleted(event);

    }


    // Consumer for CustomerChange topic



}
