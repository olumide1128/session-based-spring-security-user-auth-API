package com.lumidii.userAuth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
public class UserInMemoryRepository {
	
    private static HashMap<String, CurrentUser> REGISTERED_USERS = new HashMap<>();
    
    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;
    

    // find User Method
    public CurrentUser findUserByUsername(final String username) {
        return REGISTERED_USERS.get(username);
    }
    
    
    // Get REGISTERED_USERS
    public static HashMap<String, CurrentUser> getREGISTERED_USERS() {
		return REGISTERED_USERS;
	}
    
    
    // Clear REGISTERED_USERS
    public static void clearUsers() {
    	REGISTERED_USERS.clear();
    }


	// Save User Method
    public CurrentUser saveUser(String username, String password) {
    	
    	//Check if map repository contains user with username already
    	if(REGISTERED_USERS.containsKey(username)) {
    		return REGISTERED_USERS.get(username);
    	}
    	
      REGISTERED_USERS.put(username, buildCurrentUser(username,
      password));
      
      System.out.println("REGISTERED USERS: ");
      System.out.print(REGISTERED_USERS);
      
      return null;
    }
    

    private CurrentUser buildCurrentUser(final String username, final String password) {
        final CurrentUser currentUser = new CurrentUser();
        currentUser.setUsername(username);
        currentUser.setPassword(bcryptPasswordEncoder.encode(password));

        return currentUser;
    }
}
