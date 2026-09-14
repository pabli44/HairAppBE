package com.pvelilla.backend.hairapp.HairApp.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;
import com.pvelilla.backend.hairapp.HairApp.entities.User;
import com.pvelilla.backend.hairapp.HairApp.exceptions.RecordNotFoundException;
import com.pvelilla.backend.hairapp.HairApp.repository.UserRepository;
import com.pvelilla.backend.hairapp.HairApp.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;
	private static final String NAME_DOMAIN = "User";
	
	
	public UserServiceImpl(final UserRepository userRepository, final ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	public List<UserDTO> findAll(Optional<String> emailParam) {
		if (emailParam.isPresent()) {
			return userRepository
					.findAll(byEmail(emailParam.get()))
					.stream().map(mapper -> modelMapper.map(mapper, UserDTO.class))
					.collect(Collectors.toList());
		}
		return userRepository
				.findAll()
				.stream().map(mapper -> modelMapper.map(mapper, UserDTO.class))
				.collect(Collectors.toList());
	}

	private Specification<User> byEmail(String email) {
		return (root, query, criteriaBuilder) ->
				criteriaBuilder.equal(criteriaBuilder.lower(root.get("email")), email.toLowerCase());
	}
	
	@Override
	public UserDTO findById(Long userId) {
		return userRepository.findById(userId)
				.map(mapper -> modelMapper.map(mapper, UserDTO.class))
				.orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, userId));
	}

	@Override
	public Long save(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		userRepository.save(user);
		return user.getUserId();
	}

	@Override
	public UserDTO update(Long userId, UserDTO userDTO) {
		return userRepository.findById(userId).map(mapper -> {
			User user = modelMapper.map(userDTO, User.class);
			user.setUserId(userId);
			userRepository.save(user);
			return modelMapper.map(user, UserDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, userId));
	}

	@Override
	public UserDTO deleteById(Long userId) {
		return userRepository.findById(userId).map(mapper -> {
			userRepository.delete(mapper);
			return modelMapper.map(mapper, UserDTO.class);
		}).orElseThrow(() -> new RecordNotFoundException(NAME_DOMAIN, userId));
	}
	
}
