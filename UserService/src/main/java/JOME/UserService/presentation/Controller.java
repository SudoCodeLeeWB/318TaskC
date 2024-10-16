package JOME.UserService.presentation;


import JOME.UserService.application.UserService;
import JOME.UserService.domain.valueObject.DeliveryAddress;
import JOME.UserService.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
public class Controller {

    private final UserService userService;

    @Autowired
    public Controller(UserService _userService) {
        this.userService = _userService;
    }


    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user) {

        UserDTO response = userService.addNewUser(user);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PatchMapping("/updateAddress/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody DeliveryAddress deliveryAddress){

        UserDTO response = userService.updateUserAddress(userId , deliveryAddress);

        if(response != null){
            return ResponseEntity.ok(response);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }







}
