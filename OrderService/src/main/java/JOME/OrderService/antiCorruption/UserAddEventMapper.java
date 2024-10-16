package JOME.OrderService.antiCorruption;

import JOME.OrderService.domain.entity.Customer;
import JOME.OrderService.domain.valueObject.DeliveryAddress;
import JOME.shared_events.UserAddEventShared;

public class UserAddEventMapper {


    public static Customer mapFromEventToDomain(UserAddEventShared event){

        DeliveryAddress newAddress = new DeliveryAddress(
                event.getStreet(),
                event.getCountry(),
                event.getState(),
                event.getPostCode()
        );

        return new Customer(
                event.getUserID(),
                event.getName(),
                newAddress
        );
    }



}
