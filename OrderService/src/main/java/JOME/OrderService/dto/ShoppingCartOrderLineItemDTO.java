package JOME.OrderService.dto;


import JOME.OrderService.domain.entity.OrderOrderLineItem;
import JOME.OrderService.domain.entity.ShoppingCartOrderLineItem;

public class ShoppingCartOrderLineItemDTO {

    public ProductDTO product;
    public int quantity;


    // constructor
    public ShoppingCartOrderLineItemDTO(ShoppingCartOrderLineItem orderLineItem){
        this.quantity = orderLineItem.getQuantity();
        this.product = new ProductDTO(orderLineItem.getProduct());
    }

}
