package JOME.AnalyticsMicroservice.infrastructure.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.StreamsBuilderFactoryBeanConfigurer;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafkaStreams
public class KafkaStreamConfig {

    // Configuration for Kafka Streams
    @Bean(name = StreamsConfig.APPLICATION_ID_CONFIG)
    public StreamsBuilderFactoryBeanConfigurer kafkaStreamConfig() {

        return factoryBean -> {
            Map<String, Object> property = new HashMap<>();

            // Kafka Url Setting ( where to find kafka broker )
            property.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

            // Unique application name -> same id -> kafka treats as same instance & load balance
            property.put(StreamsConfig.APPLICATION_ID_CONFIG, "analytics-ms");

            // Default Serde
            property.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
            property.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, JsonSerde.class.getName());

            // Commit interval
            property.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000);

            // Consumer settings
            property.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

            // Apply to FactoryBean
            factoryBean.getStreamsConfiguration().putAll(property);

        };

    }


    // Kafka Stream Topology - just subscribing & serde matching
    @Bean
    public KStream<String, Object> kStream(StreamsBuilder streamsBuilder) {


       // Event serializer part is making key as string and value as Json
        JsonSerde<Object> jsonSerde = new JsonSerde<>(Object.class);
        jsonSerde.deserializer().addTrustedPackages("Jome.shared_events");


        // Subscribe to topic "Order"
        // Change the return <Object,Object> from streamsBuilder.stream to <String,Order>
        KStream<String, Object> orderStream = streamsBuilder.stream("Order" , Consumed.with(Serdes.String(),jsonSerde));

        // other Topology will be done in the KafkaStreamProcessor
        return orderStream;


    }












}
