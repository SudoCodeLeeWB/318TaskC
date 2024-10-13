package JOME.ProductService.dto;

import JOME.ProductService.domain.entity.Product;
import JOME.ProductService.domain.valueObject.CategoryEnum;

public class ProductDTO {


    public String name;
    public String description;
    public double price;
    public int stock;
    public CategoryEnum category;
    
    
    public ProductDTO(){}

    public ProductDTO( Product product){
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.category = product.getCategory();
    }






    
}
