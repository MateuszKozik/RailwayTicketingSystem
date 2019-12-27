package com.kozik.PKPTicket.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {
    
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String showProfile(Principal principal, Model model){
        String email = principal.getName();
        model.addAttribute("email", email);
        return "/views/klient/profile";
    }
}
