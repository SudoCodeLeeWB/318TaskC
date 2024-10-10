package JOME.OrderService.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;


@Configuration
public class InfrastructureConfig {

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }



}
