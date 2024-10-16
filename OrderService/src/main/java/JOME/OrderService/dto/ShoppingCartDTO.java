package JOME.OrderService.dto;

import JOME.OrderService.domain.entity.ShoppingCartOrderLineItem;
import JOME.OrderService.domain.entity.ShoppingCart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDTO {


    public Long customerId;
    public double totalPrice;
    public LocalDateTime recentUpdateTime;

    // Entities
    public List<ShoppingCartOrderLineItemDTO> orderLineItemList;

    public ShoppingCartDTO(){}

    public ShoppingCartDTO(ShoppingCart shoppingCart){
        this.customerId = shoppingCart.getCustomerId();
        this.totalPrice = shoppingCart.getTotalPrice();
        this.recentUpdateTime = shoppingCart.getRecentUpdateTime();
        this.orderLineItemList = convertListToDTO(shoppingCart.getOrderLineItemList());
    }

    private List<ShoppingCartOrderLineItemDTO> convertListToDTO(List<ShoppingCartOrderLineItem> original){

        // new List
        List<ShoppingCartOrderLineItemDTO> newList = new ArrayList<>();
        // convert to DTO
        for( ShoppingCartOrderLineItem orderLineItem : original){
            ShoppingCartOrderLineItemDTO dto = new ShoppingCartOrderLineItemDTO(orderLineItem);
            newList.add(dto);
        }
        return newList;
    }








}
