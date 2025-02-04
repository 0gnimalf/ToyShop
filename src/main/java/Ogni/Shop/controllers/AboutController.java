package Ogni.Shop.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
    @GetMapping("/")
    public String about(Model model) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().getClass().equals(String.class)){
            model.addAttribute("anonymous", true);
        }
        return "about";
    }
}
