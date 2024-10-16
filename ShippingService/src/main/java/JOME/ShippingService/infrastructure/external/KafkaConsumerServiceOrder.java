package JOME.ShippingService.infrastructure.external;

import JOME.ShippingService.application.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import JOME.shared_events.*;


@Service
@KafkaListener(topics = "Order" , groupId = "shipping-group")
public class KafkaConsumerServiceOrder {


    // Call Application layer ( dataflow sequence )
    private final ShippingService shippingService;

    @Autowired
    public KafkaConsumerServiceOrder(ShippingService shippingService) {
        this.shippingService = shippingService;
    }


    // Consumer for Order topic
    @KafkaHandler
    public void consumeOrderPlacedEvent(OrderPlacedEventShared event){

        System.out.println("Received OrderPlaced event: " + event);
        shippingService.handleOrderPlacedEvent(event);
    }


    @KafkaHandler
    public void consumeOrderCanceledEvent(OrderCanceledEventShared event){

        System.out.println("Received OrderCanceled event: " + event);
        shippingService.handleOrderCanceledEvent(event);
    }




}
