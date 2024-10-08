package JOME.OrderService.dto;


import JOME.OrderService.domain.entity.OrderLineItem;
import JOME.OrderService.domain.entity.Product;

public class OrderLineItemDTO {

    private ProductDTO product;
    private int quantity;


    // constructor
    public OrderLineItemDTO( OrderLineItem orderLineItem){
        this.quantity = orderLineItem.getQuantity();
        this.product = new ProductDTO(orderLineItem.getProduct());
    }

}
