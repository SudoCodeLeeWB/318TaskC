package JOME.OrderService.domain.service;

import JOME.OrderService.domain.valueObject.OrderLineItem;
import JOME.OrderService.domain.entity.Product;
import JOME.OrderService.domain.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShoppingCartDomainService {


    public ShoppingCart addNewProductToShoppingCart(ShoppingCart shoppingCart , Product product ,  int quantity ){

        // if there is already a same product in the shopping cart
        if(exists(shoppingCart , product)){
            return addProductQuantityFromShoppingCart(shoppingCart , product.getId() , quantity);

        }

        // if there is no same product in the shopping cart
        shoppingCart.addNewOrderLineItem(product, quantity);
        shoppingCart.calculateTotalPrice();
        shoppingCart.updateRecentUpdateTime();
        return shoppingCart;
    }

    public ShoppingCart addProductQuantityFromShoppingCart(ShoppingCart shoppingCart , Long productId , int quantity){
        shoppingCart.addOrderLineItemQuantity(productId , quantity);
        shoppingCart.calculateTotalPrice();
        shoppingCart.updateRecentUpdateTime();
        return shoppingCart;
    }

    public ShoppingCart removeProductQuantityFromShoppingCart(ShoppingCart shoppingCart , Long productId , int quantity){
        shoppingCart.removeOrderLineItemQuantity(productId , quantity);
        shoppingCart.calculateTotalPrice();
        shoppingCart.updateRecentUpdateTime();
        return shoppingCart;
    }


    // checks whether there is a same item in the ShoppingCart
    private boolean exists(ShoppingCart shoppingCart , Product product ){

        List<OrderLineItem> orderLineItemList = shoppingCart.getOrderLineItemList();
        for( OrderLineItem orderLineItem : orderLineItemList){
            if(orderLineItem.getProduct().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }





}
