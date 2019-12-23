package com.kozik.RailwayTicketingSystem.controllers;

import com.kozik.RailwayTicketingSystem.entities.Pasazer;
import com.kozik.RailwayTicketingSystem.services.PasazerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PasazerController {
    
    @Autowired private PasazerService pasazerService;
    
    @RequestMapping(value = "/pasazer/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Pasazer> pasazerList = pasazerService.listAll();
        model.addAttribute("pasazerList", pasazerList);
        
        return "views/pasazer/list";
    }
    
    @RequestMapping(value = "/pasazer/add", method = RequestMethod.GET)
    public String add(Model model){
        Pasazer pasazer = new Pasazer();
        model.addAttribute("pasazer", pasazer);
        return "views/pasazer/add";
    }
    
    @RequestMapping(value = "/pasazer/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("pasazer") Pasazer pasazer){
        pasazerService.save(pasazer);
        return "redirect:/pasazer/list";
    }
    
    @RequestMapping(value = "/pasazer/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "id") long id){
        Pasazer pasazer = pasazerService.get(id);
        model.addAttribute("pasazer", pasazer);      
        return "/views/pasazer/edit";
    }
    
     @RequestMapping(value = "/pasazer/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id, @ModelAttribute("pasazer") Pasazer pasazer){ 
        pasazer.setIdPasazera(id);
        pasazerService.save(pasazer);
        return "redirect:/pasazer/list";
    }
    
    @RequestMapping(value = "/pasazer/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        pasazerService.delete(id);
        return "redirect:/pasazer/list";
    }
    
}
