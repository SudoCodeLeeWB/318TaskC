package JOME.OrderService.dto;

import JOME.OrderService.domain.entity.OrderLineItem;
import JOME.OrderService.domain.entity.ShoppingCart;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShoppingCartDTO {


    private UUID customerId;
    private double totalPrice;
    private LocalDateTime recentUpdateTime;

    // Entities
    @OneToMany( cascade = CascadeType.ALL , orphanRemoval = true)  // set up Cascade settings
    private List<OrderLineItemDTO> orderLineItemList;

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
