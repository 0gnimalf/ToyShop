package Ogni.Shop.controllers;

import Ogni.Shop.models.UserWeb;
import Ogni.Shop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signup")
    public String getSignUp(Model model) {
        model.addAttribute("new_user", new UserWeb());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String postSignUp(Model model, @ModelAttribute("new_user") UserWeb user) {
        if (userService.existsByUsername(user.getUsername())){
            model.addAttribute("signup_error", "This username is already taken");
            return "/auth/signup";
        }
        if (userService.existsByEmail(user.getEmail())){
            model.addAttribute("signup_error", "User with this email is already exists");
            return "/auth/signup";
        }
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        if (user.getUsername().equals("admin")){
            user.setRoles(Set.of("ROLE_USER", "ROLE_ADMIN"));
        }else {user.setRoles(Set.of("ROLE_USER"));}
        userService.save(user);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String getLogIn(Model model, @RequestParam(name = "error", required = false) boolean error) {
        if (error) {
            model.addAttribute("login_error", "Invalid username or password");
        }
        model.addAttribute("auth_user", new UserWeb());
        return "auth/login";
    }
}
