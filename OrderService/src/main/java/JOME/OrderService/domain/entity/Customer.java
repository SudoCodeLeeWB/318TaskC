package JOME.OrderService.domain.entity;


import JOME.OrderService.domain.valueObject.DeliveryAddress;
import JOME.OrderService.infrastructure.persistance.CustomerRepostory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Customer {

    @Id
    private Long customerId;  // same value from Customer microservice
    private String name;
    private DeliveryAddress deliveryAddress;

    protected Customer(){}

    // To be used for ACL
    public Customer( Long customerId , String name , DeliveryAddress address){
        this.customerId = customerId;
        this.name = name;
        this.deliveryAddress = address;
    }


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
