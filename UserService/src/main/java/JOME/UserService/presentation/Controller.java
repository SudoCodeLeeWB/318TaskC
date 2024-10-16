package JOME.UserService.presentation;


import JOME.UserService.application.UserService;
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









}
