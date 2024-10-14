package JOME.AnalyticsMicroservice.external;

import JOME.shared_events.OrderCanceledEventShared;
import JOME.shared_events.OrderPlacedEventShared;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.kstream.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.state.WindowStore;


import java.time.Duration;
import java.util.function.Consumer;


@Configuration
public class KafkaStreamProcessor {


    // names of Materialization
    public final static String TOTAL_ORDERS_STORE = "total_orders";
    public final static String TOTAL_SALES_STORE = "total_sales";


    @Bean
    public Consumer<KStream<String, Object>> processEvents() {


        // inputStream = KStream , configured by KafkaStreamConfig
        return inputStream -> {

            // Deserialize the incoming events
            KStream<String, Object> deserializedStream = inputStream.mapValues(value -> {
                if (value instanceof byte[]) {
                    // Deserialize the byte[] into an appropriate event
                    try {
                        String json = new String((byte[]) value);
                        OrderPlacedEventShared event = new ObjectMapper().readValue(json, OrderPlacedEventShared.class);
                        System.out.println("Deserialized Event: " + event.getCustomerName());
                        return event;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return value;
            });


            // Logging the type of processed event
            deserializedStream.foreach((key, value) -> {
                System.out.println("Processed event: " + value.getClass().getSimpleName());
            });


//            TODO

            // Branch stream based on event type -> will be used for 2nd use-case
            KStream<String, Object>[] branches = inputStream.branch(
                    (key, value) -> value instanceof OrderPlacedEventShared, // First branch: OrderPlacedEventShared
                    (key, value) -> value instanceof OrderCanceledEventShared // Second branch: OrderCanceledEventShared
            );


            // Merging both streams (OrderPlaced and OrderCanceled) & map values for order counting - use-case 0
            KTable<Windowed<String>, Long> orderCountTable = branches[0]
                    .merge(branches[1])
                    .mapValues(value -> {
                        if (value instanceof OrderPlacedEventShared) {
                            System.out.println("Order Placed Event received.");
                            return 1L;
                        } else if (value instanceof OrderCanceledEventShared) {
                            System.out.println("Order Canceled Event received.");
                            return -1L;
                        } else {
                            return 0L; // just safety , will not happen
                        }
                    })
                    .groupByKey()
                    .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(1))) // time window -> 1 mins
                    .reduce(Long::sum,
                            Materialized.<String, Long, WindowStore<Bytes, byte[]>>as(TOTAL_ORDERS_STORE)
                                    .withKeySerde(Serdes.String())
                                    .withValueSerde(Serdes.Long())); // Value Serde -> Long

            // DEBUG :
            orderCountTable.toStream()
                    .print(Printed.<Windowed<String>, Long>toSysOut()
                            .withLabel("Total orders count by window"));
        };


    }


};




