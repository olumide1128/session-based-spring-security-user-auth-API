package com.lumidii.userAuth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService implements UserDetailsService{
	
    @Autowired
    private UserInMemoryRepository inMemoryRepository;

    
    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

        final CurrentUser currentUser = inMemoryRepository.findUserByUsername(username);
        if (currentUser == null) {
            throw new UsernameNotFoundException("Failed to find user with username: " + username);
        }

        return currentUser;
    }
}
