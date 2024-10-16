package JOME.UserService.domain.service;


import JOME.UserService.domain.entity.User;
import JOME.UserService.domain.valueObject.DeliveryAddress;
import org.springframework.stereotype.Service;

@Service
public class UserDomainService {

    public User modifyAddress( User user , DeliveryAddress deliveryAddress ){
        user.setAddress(deliveryAddress);
        return user;

    }





}
