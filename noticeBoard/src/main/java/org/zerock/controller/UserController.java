package org.zerock.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.User.LoginDto;
import org.zerock.dto.User.SignupDto;
import org.zerock.dto.User.UpdateDto;
import org.zerock.service.UserService;

@RestController
public class UserController {

	final private UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	//회원가입
	@RequestMapping(value="/User/signup", method=RequestMethod.POST)
	public void signup(@RequestBody SignupDto signupDto) {
		userService.signupUser(signupDto);
	}
	
	//로그인
	@RequestMapping(value="/User/login", method=RequestMethod.POST)
	public String login(@RequestBody LoginDto loginDto) {
		
		return userService.loginUser(loginDto);
	
	}
	
	//회원정보 조회
	@RequestMapping(value="/User/read/{identity}", method=RequestMethod.GET)
	public SignupDto read(@PathVariable String identity) {
		return userService.readUser(identity);
	}
	
	
	//회원정보 수정
	@RequestMapping(value="/User/update", method=RequestMethod.PUT)
	public void update(@RequestBody UpdateDto updateDto) {
		userService.updateUser(updateDto);
	}
	
	//회원탈퇴
	@RequestMapping(value="/User/delete/{pw}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String pw) {
		userService.deleteUser(pw);
	}
}
