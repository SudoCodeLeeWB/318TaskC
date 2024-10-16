package JOME.UserService.infrastructure.external.messaging;

import JOME.UserService.domain.event.UserAddEvent;
import JOME.UserService.domain.event.UserAddressModifyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import JOME.shared_events.*;




// A service to produce event to Kafka
@Service
public class KafkaProducerService {

    private static final String TOPIC = "UserChange";

    private final KafkaTemplate<String , Object> kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }





    public void sendUserAddEvent(UserAddEvent event){

        // convert the event
        UserAddEventShared sharedEvent = new UserAddEventShared(
                event.getUserID(),
                event.getName(),
                event.getEmail(),
                event.getPhoneNumber(),
                event.getStreet(),
                event.getState(),
                event.getCountry(),
                event.getPostCode()
        );

        kafkaTemplate.send(TOPIC, sharedEvent);

    }


    public void sendUserAddressModifiedEvent(UserAddressModifyEvent event){

        UserAddressModifyEventShared sharedEvent = new UserAddressModifyEventShared(
                event.getUserID(),
                event.getStreet(),
                event.getState(),
                event.getCountry(),
                event.getPostCode()
        );

        kafkaTemplate.send(TOPIC, sharedEvent);

    }



}
