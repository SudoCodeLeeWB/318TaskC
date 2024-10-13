package JOME.OrderService.dto;


import JOME.OrderService.domain.entity.OrderOrderLineItem;

public class OrderOrderLineItemDTO {

    public ProductDTO product;
    public int quantity;


    // constructor
    public OrderOrderLineItemDTO(OrderOrderLineItem orderLineItem){
        this.quantity = orderLineItem.getQuantity();
        this.product = new ProductDTO(orderLineItem.getProduct());
    }

}
