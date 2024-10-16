package JOME.ShippingService.antiCorruption;


import JOME.ShippingService.domain.entity.Shipment;
import JOME.ShippingService.domain.valueObject.DeliveryAddress;
import JOME.shared_events.OrderPlacedEventShared;
import org.hibernate.query.spi.SimpleHqlInterpretationImpl;

public class OrderPlacedEventMapper {


    public static Shipment mapFromEventToDomain(OrderPlacedEventShared event){

        DeliveryAddress address = new DeliveryAddress(event.getStreet() , event.getCountry(), event.getState(), event.getPostCode());
        return new Shipment(
            event.getId(),
                address
        );

    }


}
