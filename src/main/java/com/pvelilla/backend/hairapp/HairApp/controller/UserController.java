package com.pvelilla.backend.hairapp.HairApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
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

	
}
