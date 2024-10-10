package JOME.OrderService.antiCorruption;

import JOME.shared_events.ProductDeleteEventShared;

public class DeleteProductEventMapper {

    public static Long mapFromEventToDomain(ProductDeleteEventShared event){
        return event.getId();

    }

}
