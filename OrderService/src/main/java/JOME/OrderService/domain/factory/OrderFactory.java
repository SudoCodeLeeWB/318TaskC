package JOME.OrderService.domain.factory;


import JOME.OrderService.domain.entity.Customer;
import JOME.OrderService.domain.entity.Order;
import JOME.OrderService.domain.entity.ShoppingCart;
import JOME.OrderService.domain.valueObject.DeliveryAddress;
import JOME.OrderService.domain.valueObject.OrderStatus;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

// Factory pattern for Order

@Component
public class OrderFactory {

    // The initial values for the Order is managed here

    /**
     * Converts shopping cart to Order
    */
    public Order createOrder(ShoppingCart shoppingCart , Customer customer ){

        // manage init state here
        Order newOrder = new Order(
                shoppingCart.getCustomerId(),
                false,
                shoppingCart.getTotalPrice(),
                OrderStatus.Pending,
                customer.getDeliveryAddress(),
                shoppingCart.getOrderLineItemList(),
                customer.getName(),
                LocalDateTime.now()
        );

        return newOrder;

    }


}
