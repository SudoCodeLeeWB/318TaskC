package JOME.OrderService.dto;

import JOME.OrderService.domain.entity.Order;
import JOME.OrderService.domain.entity.OrderOrderLineItem;
import JOME.OrderService.domain.valueObject.DeliveryAddress;
import JOME.OrderService.domain.valueObject.OrderStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

    public Long customerId;
    public String customerName;
    public boolean paymentStatus;
    public double totalPrice;
    public LocalDateTime recentUpdateTime;

    // Entities
    public List<OrderOrderLineItemDTO> orderLineItemList;

    // value objects
    public OrderStatus orderStatus;
    public DeliveryAddress deliveryAddress;


    public OrderDTO(){}

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


    private List<OrderOrderLineItemDTO> convertListToDTO(List<OrderOrderLineItem> original){

        // new List
        List<OrderOrderLineItemDTO> newList = new ArrayList<>();
        // convert to DTO
        for( OrderOrderLineItem orderLineItem : original){
            OrderOrderLineItemDTO dto = new OrderOrderLineItemDTO(orderLineItem);
            newList.add(dto);
        }
        return newList;
    }




}
