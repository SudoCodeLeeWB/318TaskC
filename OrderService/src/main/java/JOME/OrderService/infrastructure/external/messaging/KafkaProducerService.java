package JOME.OrderService.infrastructure.external.messaging;


import JOME.OrderService.domain.event.OrderCanceledEvent;
import JOME.OrderService.domain.event.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import JOME.shared_events.*;



// A service to produce event to Kafka
@Service
public class KafkaProducerService {

    private static final String TOPIC = "Order";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Event Sender for OrderPlaced
    public void sendOrderPlacedEvent(OrderPlacedEvent event ){

        // convert the event into shared event
        OrderPlacedEventShared sharedEvent = new OrderPlacedEventShared(
                event.getId(),
                event.getCustomerId(),
                event.getCustomerName(),
                event.getTotalPrice(),
                event.getStreet(),
                event.getState(),
                event.getCountry(),
                event.getPostCode()
        );

        kafkaTemplate.send( TOPIC ,  sharedEvent);
    }


    // Event Sender for OrderCanceled
    public void sendOrderCancelledEvent(OrderCanceledEvent event ){

        OrderCanceledEventShared sharedEvent = new OrderCanceledEventShared(
                event.getId(),
                event.getCustomerId(),
                event.getTotalPrice()
        );

        kafkaTemplate.send( TOPIC , sharedEvent);
    }



}
