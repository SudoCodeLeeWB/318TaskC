package JOME.OrderService.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.springframework.kafka.retrytopic.ExceptionBasedDltDestination;

import java.io.Serializable;
import java.util.Objects;


// Order Version of ShoppingCartOrderLineItemId
@Embeddable
public class OrderOrderLineItemId implements Serializable {


    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "product_id")
    private Long productId;

    public OrderOrderLineItemId() {}

    public OrderOrderLineItemId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }



    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderOrderLineItemId that = (OrderOrderLineItemId) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId);
    }


}
