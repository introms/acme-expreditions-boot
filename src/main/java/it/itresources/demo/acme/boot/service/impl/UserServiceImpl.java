package it.itresources.demo.acme.boot.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import it.itresources.demo.acme.boot.entity.UserEntity;
import it.itresources.demo.acme.boot.meppaer.UserMapper;
import it.itresources.demo.acme.boot.model.User;
import it.itresources.demo.acme.boot.repository.UserRepository;
import it.itresources.demo.acme.boot.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		Assert.notNull(userRepository, "UserRepository cannot be null!");
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAllUsers() {		
		List<UserEntity> entities = this.userRepository.findAll();
		List<User> users = UserMapper.entitiesToModels(entities);
		
		return users;
	}

	@Override
	public Optional<User> getOneByEmail(String email) {
		Optional<UserEntity> u = this.userRepository.findByEmail(email);
		User user = null;
		
		if (u.isPresent()) {
			user = UserMapper.entityToModel(u.get());
		}
		
		return Optional.ofNullable(user);
	}
	
}
