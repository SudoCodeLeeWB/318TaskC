package JOME.OrderService.dto;


import JOME.OrderService.domain.valueObject.OrderLineItem;

public class OrderLineItemDTO {

    public ProductDTO product;
    public int quantity;


    // constructor
    public OrderLineItemDTO( OrderLineItem orderLineItem){
        this.quantity = orderLineItem.getQuantity();
        this.product = new ProductDTO(orderLineItem.getProduct());
    }

}
