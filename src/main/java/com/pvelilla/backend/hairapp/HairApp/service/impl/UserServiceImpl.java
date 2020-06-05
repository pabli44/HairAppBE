package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.config.dozer.DozerMappingBuilder;
import com.pvelilla.backend.hairapp.HairApp.config.specification.SpecificationBuilder;
import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.User;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.UserRepository;
import com.pvelilla.backend.hairapp.HairApp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private static final String NAME_DOMAIN = "User";
	
	
	public UserServiceImpl(final UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public List<UserDTO> findAll(Optional<String> userNameParam) {
		Map<String, Object> paramSpec = new HashMap<>();
		userNameParam.ifPresent(mapper -> paramSpec.put("userNameParam", userNameParam.get()));
		return userRepository
				.findAll(new SpecificationBuilder<User>(paramSpec).conjunctionEquals("[userName]", "userNameParam").build())
				.stream().map(mapper -> new DozerMappingBuilder().map(mapper, UserDTO.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public UserDTO findById(Long userId) {
		return userRepository.findById(userId)
				.map(mapper -> new DozerMappingBuilder().map(mapper, UserDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, userId));
	}

	@Override
	public Long save(UserDTO userDTO) {
		User user = new DozerMappingBuilder().map(userDTO, User.class);
		userRepository.save(user);
		return user.getUserId();
	}

	@Override
	public UserDTO update(Long userId, UserDTO userDTO) {
		return userRepository.findById(userId).map(mapper -> {
			User user = new DozerMappingBuilder().map(userDTO, User.class);
			user.setUserId(userId);
			userRepository.save(user);
			return new DozerMappingBuilder().map(user, UserDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, userId));
	}

	@Override
	public UserDTO deleteById(Long userId) {
		return userRepository.findById(userId).map(mapper -> {
			userRepository.delete(mapper);
			return new DozerMappingBuilder().map(mapper, UserDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, userId));
	}
	
}
