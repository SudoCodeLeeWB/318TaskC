package JOME.OrderService.domain.factory;


import JOME.OrderService.domain.entity.Customer;
import JOME.OrderService.domain.entity.Order;
import JOME.OrderService.domain.valueObject.OrderLineItem;
import JOME.OrderService.domain.entity.ShoppingCart;
import JOME.OrderService.domain.valueObject.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Factory pattern for Order

@Component
public class OrderFactory {

    // The initial values for the Order is managed here

    /**
     * Converts shopping cart to Order
    */
    public Order createOrder(ShoppingCart shoppingCart , Customer customer ){

        List<OrderLineItem> orderLineItemListCopy = new ArrayList<>(shoppingCart.getOrderLineItemList());

        // manage init state here
        Order newOrder = new Order(
                shoppingCart.getCustomerId(),
                false,
                shoppingCart.getTotalPrice(),
                OrderStatus.Pending,
                customer.getDeliveryAddress(),
                orderLineItemListCopy,
                customer.getName(),
                LocalDateTime.now()
        );

        return newOrder;

    }


}
