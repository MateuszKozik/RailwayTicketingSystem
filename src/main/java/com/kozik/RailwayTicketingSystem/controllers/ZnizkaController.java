package com.kozik.RailwayTicketingSystem.controllers;

import com.kozik.RailwayTicketingSystem.entities.Znizka;
import com.kozik.RailwayTicketingSystem.services.ZnizkaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ZnizkaController {
    
    @Autowired private ZnizkaService znizkaService;
    
    @RequestMapping(value = "/znizka/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Znizka> znizkaList = znizkaService.listAll();
        model.addAttribute("znizkaList", znizkaList);
     
        return "views/znizka/list";
    }
    
    @RequestMapping(value = "znizka/add", method = RequestMethod.GET)
    public String save(Model model) {
        Znizka znizka = new Znizka();
        model.addAttribute("znizka", znizka);
        return "views/znizka/add";
    }
    
    @RequestMapping(value = "znizka/add", method = RequestMethod.POST)
    public String save(@ModelAttribute("znizka") Znizka znizka){
        znizkaService.save(znizka);
        return "redirect:/znizka/list";
    }
    
     @RequestMapping(value = "znizka/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable(name = "id") long id){    
        Znizka znizka = znizkaService.get(id);
        model.addAttribute("znizka", znizka);
        return "views/znizka/edit";
    }
    
    @RequestMapping(value = "/znizka/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id, @ModelAttribute("pasazer") Znizka znizka){ 
        znizka.setIdZnizki(id);
        znizkaService.save(znizka);
        return "redirect:/znizka/list";
    }
    
    @RequestMapping(value = "znizka/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        znizkaService.delete(id);     
        return "redirect:/znizka/list";
    }
    
}
