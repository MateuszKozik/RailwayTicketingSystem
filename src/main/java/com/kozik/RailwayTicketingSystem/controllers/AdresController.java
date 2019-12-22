package com.kozik.RailwayTicketingSystem.controllers;

import com.kozik.RailwayTicketingSystem.entities.Adres;
import com.kozik.RailwayTicketingSystem.services.AdresService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdresController {
    @Autowired
    private AdresService adresService;
    
    @RequestMapping(value = "views/adres/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Adres> adresList = adresService.listAll();
                          
        model.addAttribute("header","Lista wszystkich adresów"); 
        model.addAttribute("adresList",adresList);
        return "views/adres/list";
    }
    
}
