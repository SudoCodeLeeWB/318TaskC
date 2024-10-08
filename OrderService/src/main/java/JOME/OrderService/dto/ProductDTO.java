package JOME.OrderService.dto;


import JOME.OrderService.domain.entity.Product;

public class ProductDTO {

    public String name;
    public double price;


    // constructor
    public ProductDTO( Product product){
        this.name = product.getName();
        this.price = product.getPrice();
    }

}
