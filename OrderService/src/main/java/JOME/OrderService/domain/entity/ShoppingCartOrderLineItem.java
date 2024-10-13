package JOME.OrderService.domain.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "shopping_cart_order_line_item_list")
public class ShoppingCartOrderLineItem {

    @EmbeddedId
    private ShoppingCartOrderLineItemId id;


    @ManyToOne
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;


    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;


    private int quantity;

    // constructors
    public ShoppingCartOrderLineItem(){}

    public ShoppingCartOrderLineItem( ShoppingCart shoppingcart, Product product, int quantity, Long shoppingCartId ){
        this.id = new ShoppingCartOrderLineItemId(shoppingCartId, product.getId());
        this.product = product;
        this.quantity = quantity;
        this.shoppingCart = shoppingcart;
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

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
}
