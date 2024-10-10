package JOME.OrderService.antiCorruption;

import JOME.OrderService.domain.entity.Product;
import JOME.shared_events.AddNewProductEventShared;

// ACL
public class ProductCreateEventMapper {

    public static Product mapFromEventToDomain(AddNewProductEventShared event ){
       return new Product(
               event.getId(),
               event.getName(),
               event.getPrice(),
               event.getStock(),
               event.getRecentUpdateTime()
       );
    }
}
