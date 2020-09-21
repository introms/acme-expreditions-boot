package it.itresources.demo.acme.boot.service;

import java.util.List;
import java.util.Optional;

import it.itresources.demo.acme.boot.model.User;

public interface UserService {
	
	List<User> findAllUsers(); 
	
	Optional<User> getOneByEmail(String email);

}
