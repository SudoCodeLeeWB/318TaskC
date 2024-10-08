package JOME.OrderService.domain.entity;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
public class OrderLineItem {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private UUID id;

    @OneToOne
    private Product product;
    private int quantity;

    // constructors
    public OrderLineItem(){}

    public OrderLineItem( Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    // TODO : check usage and remove it
    public OrderLineItem(UUID productId, String productName, double unitPrice, int quantity){
        this.quantity = quantity;
    }


    /**
     * @return unit price * quantity
     */
    public double getSubtotalPrice(){
        return product.getPrice() * quantity;
    }


    // Getters and setters
    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

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
