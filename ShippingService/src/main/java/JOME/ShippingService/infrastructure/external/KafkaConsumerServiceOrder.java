package JOME.ShippingService.infrastructure.external;

import JOME.ShippingService.application.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import JOME.shared_events.*;




@Service
@KafkaListener(topics = "ProductChange" , groupId = "order-group")
public class KafkaConsumerServiceOrder {


    // Call Application layer ( dataflow sequence )
    private final ShippingService shippingService;

    @Autowired
    public KafkaConsumerServiceOrder(ShippingService shippingService) {
        this.shippingService = shippingService;
    }


//     Kafka listener to consume Messages
//     Strategy : Different Event -> automatically deseralize ( try to match with correct event type )
//     If this not works, consider using event type header


    // Consumer for ProductChange topic
    @KafkaHandler
    public void consumeOrderPlacedEvent(OrderPlacedEventShared event){

        // DEBUG :
        System.out.println("Received ProductChange event: " + event);
//        shippingService.handleProductCreated(event);

    }


    @KafkaHandler
    public void consumeOrderCanceledEvent(OrderCanceledEventShared event){

        // DEBUG :
        System.out.println("Received ProductChange event: " + event);
        shippingService.handleProductUpdated(event);

    }



    // Consumer for CustomerChange topic



}
