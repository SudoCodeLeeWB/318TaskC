package JOME.OrderService.dto;

import JOME.OrderService.domain.entity.Order;
import JOME.OrderService.domain.entity.OrderLineItem;
import JOME.OrderService.domain.valueObject.DeliveryAddress;
import JOME.OrderService.domain.valueObject.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderDTO {

    private UUID customerId;
    private String customerName;
    private boolean paymentStatus;
    private double totalPrice;
    private LocalDateTime recentUpdateTime;

    // Entities
    private List<OrderLineItemDTO> orderLineItemList;

    // value objects
    private OrderStatus orderStatus;
    private DeliveryAddress deliveryAddress;

    public OrderDTO(Order order){
        this.customerId = order.getCustomerId();
        this.customerName = order.getCustomerName();
        this.paymentStatus = order.isPaymentDone();
        this.totalPrice = order.getTotalPrice();
        this.recentUpdateTime = order.getRecentUpdateTime();
        this.orderLineItemList = convertListToDTO(order.getOrderLineItemList());
        this.orderStatus = order.getOrderStatus();
        this.deliveryAddress = order.getDeliveryAddress();
    }


    private List<OrderLineItemDTO> convertListToDTO(List<OrderLineItem> original){

        // new List
        List<OrderLineItemDTO> newList = new ArrayList<>();
        // convert to DTO
        for( OrderLineItem orderLineItem : original){
            OrderLineItemDTO dto = new OrderLineItemDTO(orderLineItem);
            newList.add(dto);
        }
        return newList;
    }




}
