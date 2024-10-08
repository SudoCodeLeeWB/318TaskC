package JOME.OrderService.domain.service;

import JOME.OrderService.domain.entity.OrderLineItem;
import JOME.OrderService.domain.entity.Product;
import JOME.OrderService.domain.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


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
        return shoppingCart;
    }

    public ShoppingCart addProductQuantityFromShoppingCart(ShoppingCart shoppingCart , UUID productId , int quantity){
        shoppingCart.addOrderLineItemQuantity(productId , quantity);
        shoppingCart.calculateTotalPrice();
        return shoppingCart;
    }

    public ShoppingCart removeProductQuantityFromShoppingCart(ShoppingCart shoppingCart , UUID productId , int quantity){
        shoppingCart.removeOrderLineItemQuantity(productId , quantity);
        shoppingCart.calculateTotalPrice();
        return shoppingCart;
    }


    // checks whether there is a same item in the ShoppingCart
    private boolean exists(ShoppingCart shoppingCart , Product product ){

        List<OrderLineItem> orderLineItemList = shoppingCart.getOrderLineItemList();
        for( OrderLineItem orderLineItem : orderLineItemList){
            if(orderLineItem.getId() == product.getId()){
                return true;
            }
        }
        return false;
    }





}
