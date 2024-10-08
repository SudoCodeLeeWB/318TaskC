package JOME.OrderService.domain.valueObject;

import JOME.OrderService.domain.entity.Product;
import jakarta.persistence.*;



@Embeddable
public class OrderLineItem {

    @OneToOne
    private Product product;
    private int quantity;

    // constructors
    public OrderLineItem(){}

    public OrderLineItem( Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }


    /**
     * @return unit price * quantity
     */
    public double getSubtotalPrice(){
        return product.getPrice() * quantity;
    }



    // Getters and Setters

   public Product getProduct(){
        return product;
   }


   public void setProduct(Product product){
        this.product = product;
   }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
