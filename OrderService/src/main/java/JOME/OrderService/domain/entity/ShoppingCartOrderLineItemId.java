package JOME.OrderService.domain.entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.util.Objects;


// for persistance -> JPA to handle the shopping cartId and product ID as a composite primary key
public class ShoppingCartOrderLineItemId implements Serializable {

    @Column(name = "shopping_cart_id")
    private Long shoppingCartId;

    @Column(name = "product_id")
    private Long productId;

    public ShoppingCartOrderLineItemId() {}

    public ShoppingCartOrderLineItemId(Long shoppingCartId, Long productId) {
        this.shoppingCartId = shoppingCartId;
        this.productId = productId;
    }

    // Getters and Setters
    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    // Override equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartOrderLineItemId that = (ShoppingCartOrderLineItemId) o;
        return Objects.equals(shoppingCartId, that.shoppingCartId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCartId, productId);
    }
}
