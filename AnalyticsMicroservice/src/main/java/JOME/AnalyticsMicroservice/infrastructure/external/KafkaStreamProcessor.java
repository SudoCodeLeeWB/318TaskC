package JOME.AnalyticsMicroservice.infrastructure.external;

import JOME.shared_events.*;
import org.apache.kafka.streams.kstream.Branched;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KafkaStreamProcessor {

    @Bean
    public Consumer<KStream<String, Object>> processEvents(){

        // inputStream = KStream , configured by KafkaStreamConfig
        return inputStream -> {

            inputStream.split()
                    // when it is OrderPlaced Event
                    .branch((key, value) -> value instanceof OrderPlacedEventShared,
                            Branched.withConsumer(this::handleOrderPlacedEvent))

                    // when it is OrderCanceled Event
                    .branch((key, value) -> value instanceof OrderCanceledEventShared,
                            Branched.withConsumer(this::handleOrderCanceledEvent));
        };

    }

    // Handle OrderPlacedEventShared
    private void handleOrderPlacedEvent(KStream<String, Object> stream) {
        stream.foreach((key, value) -> {
            OrderPlacedEventShared event = (OrderPlacedEventShared) value;


            // Perform Stateful & Aggregation / windowing here


        });
    }

    // Handle OrderCanceledEventShared
    private void handleOrderCanceledEvent(KStream<String, Object> stream) {
        stream.foreach((key, value) -> {
            OrderCanceledEventShared event = (OrderCanceledEventShared) value;



            // Add your real-time analysis logic here




        });
    }


}
