package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Pociag;
import com.kozik.PKPTicket.services.PociagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PociagController {
    
    @Autowired
    private PociagService pociagService;
    
    @RequestMapping(value = "/pociag/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Pociag> pociagList = pociagService.listAll();
        model.addAttribute("pociagList", pociagList);
        return "views/pociag/list";
    }
    
    @RequestMapping(value = "/pociag/add", method = RequestMethod.GET)
    public String add(Model model){
        Pociag pociag = new Pociag();
        model.addAttribute("pociag", pociag);
        return "views/pociag/add";
    }
    
    @RequestMapping(value = "/pociag/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("pociag") Pociag pociag){
        pociagService.save(pociag);
        return "redirect:/pociag/list";
    }
    
    @RequestMapping(value = "/pociag/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable(name = "id") long id){
        Pociag pociag = pociagService.get(id);
        model.addAttribute("pociag", pociag);
        return "views/pociag/edit";
    }
    
    @RequestMapping(value = "/pociag/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @ModelAttribute("pociag") Pociag pociag){
        pociag.setIdPociagu(id);
        pociagService.save(pociag);
        return "redirect:/pociag/list";
    }
    
    @RequestMapping(value = "/pociag/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        pociagService.delete(id);
        return "redirect:/pociag/list";
    }
}
