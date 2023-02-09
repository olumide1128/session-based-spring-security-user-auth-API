package com.lumidii.userAuth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lumidii.userAuth.user.UserInMemoryRepository;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class ListController {
	
    @Autowired
    public UserInMemoryRepository inMemoryRepository;

    
    @GetMapping("/list")
    public List<String> getListItems() {
    	
    	System.out.println(inMemoryRepository.getREGISTERED_USERS());
    	
        return List.of("1", "2", "3");
               
    }
}
