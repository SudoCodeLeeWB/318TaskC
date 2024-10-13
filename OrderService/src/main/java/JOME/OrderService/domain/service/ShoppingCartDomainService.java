package JOME.OrderService.domain.service;

import JOME.OrderService.domain.entity.ShoppingCartOrderLineItem;
import JOME.OrderService.domain.entity.Product;
import JOME.OrderService.domain.entity.ShoppingCart;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ShoppingCartDomainService {

    @Transactional
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

    @Transactional
    public ShoppingCart addProductQuantityFromShoppingCart(ShoppingCart shoppingCart , Long productId , int quantity){
        shoppingCart.addOrderLineItemQuantity(productId , quantity);
        shoppingCart.calculateTotalPrice();
        shoppingCart.updateRecentUpdateTime();
        return shoppingCart;
    }

    @Transactional
    public ShoppingCart removeProductQuantityFromShoppingCart(ShoppingCart shoppingCart , Long productId , int quantity){
        shoppingCart.removeOrderLineItemQuantity(productId , quantity);
        shoppingCart.calculateTotalPrice();
        shoppingCart.updateRecentUpdateTime();
        return shoppingCart;
    }


    // checks whether there is a same item in the ShoppingCart
    private boolean exists(ShoppingCart shoppingCart , Product product ){

        List<ShoppingCartOrderLineItem> orderLineItemList = shoppingCart.getOrderLineItemList();
        for( ShoppingCartOrderLineItem orderLineItem : orderLineItemList){
            if(orderLineItem.getProduct().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }





}
