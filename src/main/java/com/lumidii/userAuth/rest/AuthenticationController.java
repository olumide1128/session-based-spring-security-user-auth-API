package com.lumidii.userAuth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lumidii.userAuth.dto.ResponseDTO;
import com.lumidii.userAuth.dto.UserDTO;
import com.lumidii.userAuth.session.SessionRegistry;
import com.lumidii.userAuth.user.CurrentUser;
import com.lumidii.userAuth.user.UserInMemoryRepository;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    public AuthenticationManager manager;
    
    @Autowired
    public SessionRegistry sessionRegistry;

    @Autowired
    public UserInMemoryRepository inMemoryRepository;
    
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO user) {
    	
    	String username = user.getUsername();
    	String password = user.getPassword();
    	
    	//pass the user details to inMemory Save Method
    	CurrentUser current_user = inMemoryRepository.saveUser(username, password);
    	
    	if(current_user != null) {
    		return ResponseEntity
    	            .status(HttpStatus.BAD_REQUEST)
    	            .body("User with username "+username+" already Exists!");
    	}
    	
     
		return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body("User Registered Successfully!");
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserDTO user) {
    	System.out.println("REGISTERED USERS: ");
    	System.out.print(inMemoryRepository.getREGISTERED_USERS());
    	
        manager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
                       
                    
        final String sessionId = sessionRegistry.registerSession(user.getUsername());
        ResponseDTO response = new ResponseDTO();
        response.setSessionId(sessionId);

        return ResponseEntity.ok(response);
            


    }
}
