package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.services.BiletService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BiletController {
    @Autowired
    private BiletService biletService;
    
    @RequestMapping(value = "/bilet/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Bilet> biletList = biletService.listAll();
        model.addAttribute("biletList", biletList);
        return "views/bilet/list";
    }
    
    @RequestMapping(value = "/bilet/add", method = RequestMethod.GET)
    public String add(Model model){
        Bilet bilet = new Bilet();
        model.addAttribute("bilet", bilet);
        return "views/bilet/add";
    }
    
    @RequestMapping(value = "/bilet/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("bilet") Bilet bilet){
        biletService.save(bilet);
        return "redirect:/bilet/list";
    }
    
    @RequestMapping(value = "/bilet/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(name = "id") long id, Model model){
        Bilet bilet = biletService.get(id);
        model.addAttribute("bilet", bilet);
        return "/views/bilet/edit";
    }
    
    @RequestMapping(value = "/bilet/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @ModelAttribute("bilet") Bilet bilet){
        bilet.setIdBiletu(id);
        biletService.save(bilet);
        return "redirect:/bilet/list";
    }
    
    @RequestMapping(value = "/bilet/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        biletService.delete(id);
        return "redirect:/bilet/list";
    }
}
