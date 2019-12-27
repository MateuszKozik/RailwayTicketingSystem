package com.kozik.PKPTicket.controllers;

import org.springframework.stereotype.Controller;
import com.kozik.PKPTicket.entities.Uzytkownik;
import com.kozik.PKPTicket.entities.Uprawnienia;
import com.kozik.PKPTicket.services.UprawnieniaService;
import com.kozik.PKPTicket.services.UzytkownikService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {
    @Autowired private UzytkownikService uzytkownikService;
    @Autowired private UprawnieniaService uprawnieniaService;
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
     Uzytkownik uzytkownik = new Uzytkownik();
     model.addAttribute("uzytkownik", uzytkownik);
     return "views/register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid Uzytkownik uzytkownik, BindingResult bindingResult,
            Model model){
        if(bindingResult.hasErrors()){
              return "views/register"; 
        }
        if(uzytkownikService.isUserPresent(uzytkownik.getEmail())){
            model.addAttribute("exist", true);
             return "views/register"; 
        } 
        Uprawnienia uprawnienia = uprawnieniaService.getUser();
        List<Uprawnienia> uprawnieniaList = new ArrayList<Uprawnienia>();
        uprawnieniaList.add(uprawnienia);
        uzytkownikService.save(uzytkownik, uprawnieniaList);
        return "views/registerSuccess";
    }
}
