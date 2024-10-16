package JOME.OrderService.domain.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "order_order_line_item_list")
public class OrderOrderLineItem {

    @EmbeddedId
    private OrderOrderLineItemId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    // constructors
    public OrderOrderLineItem(){}

    public OrderOrderLineItem( Order order, Product product, int quantity){
        this.id = new OrderOrderLineItemId(order.getId(), product.getId());
        this.product = product;
        this.quantity = quantity;
        this.order = order;

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
