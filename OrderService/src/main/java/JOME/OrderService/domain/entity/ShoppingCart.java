package JOME.OrderService.domain.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShoppingCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private double totalPrice;
    private LocalDateTime recentUpdateTime;

    // Entities
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "shoppingCart")
    private List<ShoppingCartOrderLineItem> orderLineItemList = new ArrayList<>();

    // Constructor
    protected ShoppingCart(){}


    /**
     * Will be used for ShoppingCartFactory
     * */
    public ShoppingCart(Long customerId, double totalPrice, LocalDateTime recentUpdateTime) {
        this.customerId = customerId;
        this.totalPrice = totalPrice;
        this.recentUpdateTime = recentUpdateTime;
    }


    /** Business Logic :
     * Add NEW Product to the OrderLineItem List
     * If there is already a same order ( same product Id ) -> it will
     * just call addOrderLineItemQuantity()
     */
    public void addNewOrderLineItem( Product product , int quantity){

        // invalid quantity number - do nothing
        if ( quantity <= 0){
            return ;
        }

        if( quantity > 10 ){
            quantity = 10;
        }

        ShoppingCartOrderLineItem existingItem = findProduct(product.getId());
        if (existingItem != null) {
            addOrderLineItemQuantity(product.getId(), quantity);
        } else {
            ShoppingCartOrderLineItem newItem = new ShoppingCartOrderLineItem( this, product, quantity, this.id);
            orderLineItemList.add(newItem);
        }

    }


    /**
     * Business Logic :
     * First it will try to add the quantity to the existing OrderLineItem
     * After adding, if the quantity is bigger than 10, then It will automatically make is as 10
     * @return If added : return True / No such Item in the OrderLineItem : return False
     */
    public void addOrderLineItemQuantity(Long productId, int quantity){

        // invalid quantity number - do nothing
        if ( quantity <= 0){
            return ;
        }

        // find existing OrderLineItem
        ShoppingCartOrderLineItem target = findProduct(productId);

        if( target == null){
            return;
        }

        int updatedQuantity = quantity + target.getQuantity();

        // business logic handle ( must be lesser than 10 )
        if( updatedQuantity > 10 ){
            updatedQuantity = 10;
        }

        // update the quantity
        target.setQuantity(updatedQuantity);

   }






    /**
     * Business Logic :
     * First it will try to remove the quantity of OrderLineItem.
     * After deleting , if the quantity is smaller than 0, then It will remove the OrderLIneItem itself
     * @return If removed : return True / No such Item in the OrderLineItem : return False
     */
    public void removeOrderLineItemQuantity( Long productId , int quantity){


        // invalid quantity number - do nothing
        if ( quantity <= 0){
            return ;
        }

        // find existing OrderLineItem
        ShoppingCartOrderLineItem target = findProduct(productId);

        if( target == null){
            return;
        }

        int updatedQuantity = target.getQuantity() - quantity;

        if (updatedQuantity <= 0) {
            orderLineItemList.remove(target);
        } else {
            target.setQuantity(updatedQuantity);
        }

    }


    // private helper method to find the product in the OrderLineItem
    private ShoppingCartOrderLineItem findProduct(Long productId ){
        for(ShoppingCartOrderLineItem orderLineItem : orderLineItemList){
            if(orderLineItem.getProduct().getId().equals(productId)){
                return orderLineItem;
            }
        }
        // if no such item
        return null;
    }



    /**
     * Calculate total price
     */
    public void calculateTotalPrice(){
        double sum = 0.0 ;

        // iterate & calculate sum
        for(ShoppingCartOrderLineItem orderLineItem : orderLineItemList){
            sum += orderLineItem.getSubtotalPrice();
        }

        this.totalPrice = sum;
    }



    /**
     * Updates the recent aggregate modified time
     */
    public void updateRecentUpdateTime(){
        this.recentUpdateTime = LocalDateTime.now();
    }


    // getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getRecentUpdateTime() {
        return recentUpdateTime;
    }

    public void setRecentUpdateTime(LocalDateTime recentUpdateTime) {
        this.recentUpdateTime = recentUpdateTime;
    }

    public List<ShoppingCartOrderLineItem> getOrderLineItemList() {
        return orderLineItemList;
    }

    public void setOrderLineItemList(List<ShoppingCartOrderLineItem> orderLineItemList) {
        this.orderLineItemList = orderLineItemList;
    }
}
