package JOME.OrderService.dto;

import JOME.OrderService.domain.valueObject.OrderLineItem;
import JOME.OrderService.domain.entity.ShoppingCart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {


    public Long customerId;
    public double totalPrice;
    public LocalDateTime recentUpdateTime;

    // Entities
    public List<OrderLineItemDTO> orderLineItemList;

    public ShoppingCartDTO(){}

    public ShoppingCartDTO(ShoppingCart shoppingCart){
        this.customerId = shoppingCart.getCustomerId();
        this.totalPrice = shoppingCart.getTotalPrice();
        this.recentUpdateTime = shoppingCart.getRecentUpdateTime();
        this.orderLineItemList = convertListToDTO(shoppingCart.getOrderLineItemList());
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
