package com.pvelilla.backend.hairapp.HairApp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pvelilla.backend.hairapp.HairApp.domain.ResponseMapDTO;
import com.pvelilla.backend.hairapp.HairApp.domain.UserDTO;
import com.pvelilla.backend.hairapp.HairApp.service.UserService;

@RestController
@RequestMapping("/apiv1/users")
public class UserController {

	private UserService userService;
	
	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}
	
	@CrossOrigin
	@PostMapping
	public ResponseMapDTO save(@RequestBody @Valid UserDTO userDTO) {
		return new ResponseMapDTO("recordId", userService.save(userDTO));
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/{userId}")
	public UserDTO deleteById(@PathVariable Long userId) {
		return userService.deleteById(userId);
	}

	@CrossOrigin
	@PutMapping(value = "/{userId}")
	public UserDTO updateById(@PathVariable Long userId, @RequestBody @Valid UserDTO userDTO) {
		return userService.update(userId, userDTO);
	}

	@CrossOrigin
	@GetMapping(value = "/{userId}")
	public UserDTO getUserById(@PathVariable Long userId) {
		return userService.findById(userId);
	}
	
	@CrossOrigin
	@GetMapping
	public List<UserDTO> findALlRecords(@RequestParam(name = "userNameParam") Optional<String> userNameParam) {
		return userService.findAll(userNameParam);
	}

	
}
