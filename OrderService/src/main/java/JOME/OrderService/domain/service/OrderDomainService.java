package JOME.OrderService.domain.service;


import JOME.OrderService.domain.entity.Order;
import org.springframework.stereotype.Service;



@Service
public class OrderDomainService {


    public Order placeOrder( Order newOrder , boolean paymentSuccess ){

        if(paymentSuccess){
            newOrder.completeOrder();
            newOrder.markPaymentAsDone();
        }else{
            newOrder.failedOrder();
            // payment default : false
        }
        newOrder.updateRecentUpdateTime();
        return newOrder;

    }


    public Order cancelOrder (Order order){
        order.cancelOrder();
        order.updateRecentUpdateTime();
        return order;
    }


}
