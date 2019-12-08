
package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.dto.User.LoginDto;
import org.zerock.dto.User.SignupDto;
import org.zerock.dto.User.UpdateDto;
import org.zerock.entity.User;
import org.zerock.repository.UserRepository;


@Service
public class UserService {
	
	final private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	//회원가입
	public void signupUser(SignupDto signupDto) {
		
		User signupUser=new User();
		
		signupUser.setName(signupDto.getName());
		signupUser.setAge(signupDto.getAge());
		signupUser.setPhone(signupDto.getPhone());
		signupUser.setIdentity(signupDto.getIdentity());
		signupUser.setPassword(signupDto.getPassword());
		
		userRepository.save(signupUser);
		
	}
	
	//로그인
	public String loginUser(LoginDto loginDto) {
		
		
		User loginUser=userRepository.findByIdentity(loginDto.getIdentity());
		
		//정보 없을 때
		if(loginUser==null) {
		
			return "dont have id";
		}
		//비밀번호가 틀렸을 때
		else if(!loginUser.getPassword().equals(loginDto.getPassword())) {
			return "wrong password";
		}
		
		return "login Success";
		
	}
	
	//회원정보조회
	public SignupDto readUser(String identity) {
		
		User readUser=userRepository.findByIdentity(identity);
		
		SignupDto signupDto=new SignupDto();
		
		signupDto.setName(readUser.getName());
		signupDto.setAge(readUser.getAge());
		signupDto.setPhone(readUser.getPhone());
		signupDto.setIdentity(readUser.getIdentity());
		signupDto.setPassword(readUser.getPassword());
		
		return signupDto;
	}
	
	
	//회원정보수정
	public void updateUser(UpdateDto updateDto) {
		
		User updateUser=userRepository.findByIdentity(updateDto.getIdentity());
		
		updateUser.setPassword(updateDto.getPassword());
		updateUser.setName(updateDto.getName());
		updateUser.setAge(updateDto.getAge());
		updateUser.setPhone(updateDto.getPhone());
		
		userRepository.save(updateUser);
	}
	
	//회원탈퇴
	public void deleteUser(String password) {
		
		User deleteUser=userRepository.findByPassword(password);
		
		userRepository.delete(deleteUser);

	}
	
	
}
