package com.pvelilla.backend.hairapp.HairApp.service;

import java.util.List;
import java.util.Optional;

import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;

public interface UserService {
	
	List<UserDTO> findAll(Optional<String> emailParam);
	
	UserDTO findById(Long userId);
	
	Long save(UserDTO userDTO);

	UserDTO update(Long currencyId, UserDTO userDTO);

	UserDTO deleteById(Long userId);
	
}
