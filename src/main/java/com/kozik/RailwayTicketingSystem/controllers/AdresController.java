/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    @RequestMapping(value = "adres/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Adres> adresList = adresService.listAll();
                          
        model.addAttribute("header","Lista wszystkich adresów"); 
        model.addAttribute("adresList",adresList);
        return "adres/list";
    }
    
}
