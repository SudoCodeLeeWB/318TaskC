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

        return inputStream -> {


            // Step 1: Deserialize the incoming events
            KStream<String, Object> deserializedStream = inputStream.mapValues(value -> {
                if (value instanceof byte[]) {
                    try {
                        String json = new String((byte[]) value); // Deserialize the byte[] into an appropriate event
                        try{ // Try to deserialize into OrderPlacedEventShared
                            OrderPlacedEventShared event = new ObjectMapper().readValue(json, OrderPlacedEventShared.class);
                            // having customer name => it is OrderPlacedSharedEvent
                            if(event.getCustomerName() != null){ return event; }
                        } catch (Exception ignored){}
                        try{ // Attempt to deserialize into OrderCanceled Event ( not OrderPlaced -> OrderCanceled )
                            return new ObjectMapper().readValue(json, OrderCanceledEventShared.class);
                        }catch (Exception ignored){}
                    } catch (Exception e) {e.printStackTrace();}}
                return value;
            });


            // Fork 1 : from deserialized Stream , counting OrderNumbers
            // Configure Keys for KTable
            KStream<String, Object> streamWithKeysAsCustomerId = deserializedStream.selectKey((key, value) -> {
                if (value instanceof OrderPlacedEventShared) {
                    return String.valueOf(((OrderPlacedEventShared) value).getCustomerId());
                } else if (value instanceof OrderCanceledEventShared) {
                    return String.valueOf(((OrderCanceledEventShared) value).getCustomerId());
                } else {
                    return key;
                }
            });

            // Aggregation
            KTable<Windowed<String>, Long> orderCountTable = streamWithKeysAsCustomerId
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
                    .groupByKey(Grouped.with(Serdes.String(), Serdes.Long()))
                    .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(1))) // time window -> 1 mins
                    .reduce(Long::sum,
                            Materialized.<String, Long, WindowStore<Bytes, byte[]>>as(TOTAL_ORDERS_STORE)
                                    .withKeySerde(Serdes.String())
                                    .withValueSerde(Serdes.Long())); // Value Serde -> Long

            // DEBUG :
            orderCountTable.toStream()
                    .print(Printed.<Windowed<String>, Long>toSysOut()
                            .withLabel("Total orders count by window"));



            // Fork 2 : calculating orders per country in certain timeframe
            // Convert Stream -> Mapping
            KStream<String, Double> streamWithKeysAsCountry = deserializedStream
                    .selectKey((key, value) -> {
                        if (value instanceof OrderPlacedEventShared) {
                            return String.valueOf(((OrderPlacedEventShared) value).getCountry());
                        } else if (value instanceof OrderCanceledEventShared) {
                            return String.valueOf(((OrderCanceledEventShared) value).getCountry());
                        } else {
                            return key;
                        }})
                    .mapValues( value ->  {
                        if (value instanceof OrderPlacedEventShared) {
                            return ((OrderPlacedEventShared) value).getTotalPrice();
                        }else if (value instanceof OrderCanceledEventShared) {
                            return ((OrderCanceledEventShared) value).getTotalPrice() * (-1);
                        } else {
                            return 0.0;
                        }});


            // Aggregation
            KTable<Windowed<String>, Double> salesPerCountryTable = streamWithKeysAsCountry
                    .groupByKey(Grouped.with(Serdes.String() ,Serdes.Double()))
                    .windowedBy(TimeWindows.ofSizeWithNoGrace(Duration.ofMinutes(1)))
                    .reduce( Double::sum,
                            Materialized.<String, Double, WindowStore<Bytes, byte[]>>as(TOTAL_SALES_STORE)
                                    .withKeySerde(Serdes.String())
                                    .withValueSerde(Serdes.Double()));

            //DEBUG :
            salesPerCountryTable.toStream()
                    .print(Printed.<Windowed<String>, Double>toSysOut()
                            .withLabel("Total Saless count by country & window"));











        };


    }


};




