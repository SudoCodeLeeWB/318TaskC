package JOME.OrderService.antiCorruption;

import JOME.OrderService.domain.entity.Customer;
import JOME.OrderService.domain.valueObject.DeliveryAddress;
import JOME.shared_events.UserAddEventShared;
import JOME.shared_events.UserAddressModifyEventShared;
import org.apache.commons.lang3.tuple.Pair;

public class UserAddressModifyEventMapper {

    public static Customer maprFromEventToDomain( Customer target, UserAddressModifyEventShared event){

        DeliveryAddress newAddress =  new DeliveryAddress(
                event.getStreet(),
                event.getCountry(),
                event.getState(),
                event.getPostCode()
        );

        target.setDeliveryAddress(newAddress);
        return target;

    }



}
