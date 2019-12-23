package com.kozik.RailwayTicketingSystem.controllers;

import com.kozik.RailwayTicketingSystem.entities.Adres;
import com.kozik.RailwayTicketingSystem.services.AdresService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdresController {
    @Autowired
    private AdresService adresService;
    
    @RequestMapping(value = "views/adres/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Adres> adresList = adresService.listAll();
        model.addAttribute("adresList",adresList);
        return "/views/adres/list";
    }
    
    @RequestMapping(value = "views/adres/add", method = RequestMethod.GET)
    public String save(Model model) {
        Adres adres = new Adres();
        model.addAttribute("adres", adres);
        return "/views/adres/add";
    }
    
    @RequestMapping(value = "views/adres/add", method = RequestMethod.POST)
    public String save(@ModelAttribute("adres") Adres adres){
        adresService.save(adres);
        return "redirect:/views/adres/list";
    }
    
     @RequestMapping(value = "views/adres/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "id") long id){
        
        Adres adres = adresService.get(id);
        model.addAttribute("adres", adres);
        return "/views/adres/edit";
    }
    
    @RequestMapping(value = "views/adres/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id, @ModelAttribute("adres") Adres adres){ 
        System.out.println(id);
        adres.setIdAdresu(id);
        adresService.save(adres);
        return "redirect:/views/adres/list";
    }
    
}
