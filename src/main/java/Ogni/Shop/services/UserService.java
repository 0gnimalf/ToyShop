package Ogni.Shop.services;

import Ogni.Shop.models.UserWeb;
import Ogni.Shop.repositories.UserRepo;
import Ogni.Shop.security.UserDetailsCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserWeb userWeb = userRepo.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException("Username " + username + " not found"));
        return new UserDetailsCustom(userWeb.getUsername(), userWeb.getPassword(), userWeb.getRoles());
    }
}
