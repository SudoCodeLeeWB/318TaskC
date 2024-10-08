package JOME.OrderService.presentation;


import JOME.OrderService.application.OrderService;
import JOME.OrderService.dto.OrderDTO;
import JOME.OrderService.dto.ShoppingCartDTO;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Controller {


    private final OrderService orderService;


    @Autowired
    public Controller ( OrderService orderService){
        this.orderService = orderService;
    }



    // Add REST Mappings HERE

    // usecase 1 : Add new product to the shoppingCart
    @PatchMapping("/addNew/{customerId}/{productId}/{quantity}")
    public ResponseEntity<ShoppingCartDTO> addNewProductToShoppingCart(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @PathVariable int quantity){

        ShoppingCartDTO response = orderService.addNewOrderLineItemToShoppingCart(customerId , productId , quantity);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // usecase 2 : add product quantity to the shopping cart
    @PatchMapping("/add/{customerId}/{productId}/{quantity}")
    public ResponseEntity<ShoppingCartDTO> addProductQuantityToShoppingCart(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @PathVariable int quantity){

        ShoppingCartDTO response = orderService.addOrderLineItemQuantity(customerId , productId , quantity);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // usecase 3 : remove product quantity from the shopping cart
    @PatchMapping("/remove/{customerId}/{productId}/{quantity}")
    public ResponseEntity<ShoppingCartDTO> removeProductQuantityFromShoppingCart(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @PathVariable int quantity){

        ShoppingCartDTO response = orderService.deleteOrderLineItemQuantity(customerId , productId , quantity);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    // usecase 4 : place order
    @PatchMapping("/placeOrder/{customerId}")
    public ResponseEntity<OrderDTO> placeOrder(
            @PathVariable Long customerId){

        OrderDTO response = orderService.placeOrder(customerId);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // usecase 5 : Cancel Order
    @PatchMapping("/cancelOrder/{orderId}")
    public ResponseEntity<OrderDTO> cancelOrder(
            @PathVariable Long orderId){

        OrderDTO response = orderService.cancelOrder(orderId);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
