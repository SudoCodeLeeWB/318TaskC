package JOME.OrderService.domain.factory;


import JOME.OrderService.domain.entity.*;
import JOME.OrderService.domain.valueObject.OrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Factory pattern for Order

@Component
public class OrderFactory {

    // The initial values for the Order is managed here

    /**
     * Converts shopping cart to Order
    */
    public Order createOrder(ShoppingCart shoppingCart , Customer customer ){

        Order newOrder = new Order();

        // conversion from Shopping cart to order --> we are managing different OrderLineItem
        // => we need to convert from ShoppingCartOrderLineItem To  OrderOrderLineItem

        // convert the shoppingCart's ShoppingCartOrderLineItem into orderOrderLineItem ( mapping )
        List<OrderOrderLineItem> orderOrderLineItemList = new ArrayList<>();
        List<ShoppingCartOrderLineItem> shoppingCartOrderLineItems = shoppingCart.getOrderLineItemList();
        for (ShoppingCartOrderLineItem orderLineItem : shoppingCartOrderLineItems) {

            Product includedProduct = orderLineItem.getProduct();
            int includedQuantity = orderLineItem.getQuantity();

            OrderOrderLineItem newOrderOrderLineitem = new OrderOrderLineItem(newOrder, includedProduct , includedQuantity);
            orderOrderLineItemList.add(newOrderOrderLineitem);
        }

        //DEBUG : check OrderOrderLineItemList
        System.out.println(orderOrderLineItemList);

        newOrder.orderSetter(
                shoppingCart.getCustomerId(),
                false,
                shoppingCart.getTotalPrice(),
                OrderStatus.Pending,
                customer.getDeliveryAddress(),
                orderOrderLineItemList,
                customer.getName(),
                LocalDateTime.now()
        );

        return newOrder;

    }


}
