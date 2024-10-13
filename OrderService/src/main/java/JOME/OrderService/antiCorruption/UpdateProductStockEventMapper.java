package JOME.OrderService.antiCorruption;

import JOME.OrderService.domain.entity.Product;
import JOME.shared_events.UpdateProductStockEventShared;

public class UpdateProductStockEventMapper {


    public static Product mapFromEventToDomain(Product original , UpdateProductStockEventShared event){

        // update the stock
        original.setStock(event.getStock());
        original.setRecentUpdateTime(event.getRecentUpdateTime());
        return original;

    }

}
