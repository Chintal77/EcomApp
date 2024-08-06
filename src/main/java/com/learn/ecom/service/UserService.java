package com.learn.ecom.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecom.model.UserDtls;

public interface UserService {

	public UserDtls saveUser(UserDtls user);

	public UserDtls getUserByEmail(String email);

	public String getUsernameByEmail(@RequestParam String email);

	public List<UserDtls> getUsers(String role);

	public Boolean updateAccountStatus(Integer id, Boolean status);

	public void increaseFailedAttempt(UserDtls user);

	public void userAccountLock(UserDtls user);

	public boolean unlockAccountTimeExpired(UserDtls user);

	public void resetAttempt(int userId);

	public void updateUserResetToken(String email, String resetToken);

	public UserDtls getUserByToken(String token);

	public UserDtls updateUser(UserDtls user);
	
	public UserDtls updateUserProfile(UserDtls user,MultipartFile img);

}
