package JOME.ShippingService.antiCorruption;

import JOME.shared_events.OrderCanceledEventShared;

public class OrderCanceledEventMapper {

    public static Long mapFromEventToDomain(OrderCanceledEventShared event){
        return event.getId();
    }

}
