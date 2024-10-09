package JOME.ProductService.infrastructure.external.messaging;


import JOME.ProductService.domain.event.AddNewProductEvent;
import JOME.ProductService.domain.event.ProductDeleteEvent;
import JOME.ProductService.domain.event.UpdateProductStockEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


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
        kafkaTemplate.send( TOPIC ,event);
    }


    // Event sender for Product Update
    public void sendProductUpdateEvent(UpdateProductStockEvent event ){
        kafkaTemplate.send( TOPIC ,event);
    }


    // Event Sender for Product Delete
    public void sendProductDeleteEvent(ProductDeleteEvent event ){
        kafkaTemplate.send( TOPIC ,event);
    }



}
