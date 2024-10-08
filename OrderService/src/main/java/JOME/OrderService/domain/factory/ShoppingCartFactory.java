package JOME.OrderService.domain.factory;

import JOME.OrderService.domain.entity.ShoppingCart;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;


// declaring it as a spring managed bean
@Component
public class ShoppingCartFactory {
    public ShoppingCart createNewShoppingCart(UUID customerId){
        return new ShoppingCart(customerId, 0.0 , LocalDateTime.now() , new ArrayList<>());
    }

}
