package JOME.ProductService.infrastructure.external.messaging;


import JOME.ProductService.domain.event.AddNewProductEvent;
import JOME.ProductService.domain.event.ProductDeleteEvent;
import JOME.ProductService.domain.event.UpdateProductStockEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import JOME.shared_events.*;



// A service to produce event to Kafka
@Service
public class KafkaProducerService {

    private static final String TOPIC = "ProductChange";

    private final KafkaTemplate<String , Object> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    // Event sender for Product Add
    public void sendProductAddEvent(AddNewProductEvent event){

        // change the event into shared event

        AddNewProductEventShared sharedEvent = new AddNewProductEventShared(
                event.getId(),
                event.getName(),
                event.getDescription(),
                event.getPrice(),
                event.getStock(),
                event.getRecentUpdateTime()
        );

        kafkaTemplate.send( TOPIC ,sharedEvent);


        System.out.println(sharedEvent);
    }


    // Event sender for Product Update
    public void sendProductUpdateEvent(UpdateProductStockEvent event ){

        UpdateProductStockEventShared sharedEvent = new UpdateProductStockEventShared(
                event.getId(),
                event.getStock(),
                event.getRecentUpdateTime()
        );

        kafkaTemplate.send( TOPIC ,sharedEvent);
    }


    // Event Sender for Product Delete
    public void sendProductDeleteEvent(ProductDeleteEvent event ){

        ProductDeleteEventShared sharedEvent = new ProductDeleteEventShared(
                event.getId()
        );

        kafkaTemplate.send( TOPIC ,sharedEvent);
    }



}
