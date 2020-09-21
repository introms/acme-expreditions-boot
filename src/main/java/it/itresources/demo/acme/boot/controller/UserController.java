package it.itresources.demo.acme.boot.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.itresources.demo.acme.boot.model.User;
import it.itresources.demo.acme.boot.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		Assert.notNull(userService, "Injected userService cannot be null");
		this.userService = userService;
	}

	@GetMapping("")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if(users.isEmpty()){
            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
	
	@GetMapping("/{email}")
    public ResponseEntity<User> getUser(@PathVariable("email") String email) {
        LOGGER.info("Fetching User with email " + email);
        
        Optional<User> userOptional = this.userService.getOneByEmail(email);
        if (userOptional.isPresent()) {
        	User user = userOptional.get();
        	return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
	
}
