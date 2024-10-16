package JOME.UserService.dto;

import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.valueObject.Address;

public class UserDTO {

    public 




    public Long orderID;
    public Address shippingStatus;

    public UserDTO() {}

    public UserDTO(Shipment shipment) {
        this.orderID = shipment.getOrderID();
        this.shippingStatus = shipment.getShippingStatus();
    }


}
