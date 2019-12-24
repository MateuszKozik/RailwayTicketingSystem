package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.services.KursService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KursController {
    @Autowired
    private KursService kursService;
    
    @RequestMapping(value = "/kurs/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Kurs> kursList = kursService.listAll();
        model.addAttribute("kursList", kursList);
        return "views/kurs/list";
    }
    
    @RequestMapping(value = "/kurs/add", method = RequestMethod.GET)
    public String add(Model model){
        Kurs kurs = new Kurs();
        model.addAttribute("kurs", kurs);
        return "view/kurs/add";
    }
    
    @RequestMapping(value = "/kurs/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("kurs") Kurs kurs){
       kursService.save(kurs);
       return "redirect:/kurs/list";
    }
    
    @RequestMapping(value = "/kurs/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable(name = "id") long id){
        Kurs kurs = kursService.get(id);
        model.addAttribute("kurs", kurs);
        return "view/kurs/edit";
    }
    
    @RequestMapping(value = "/kurs/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @ModelAttribute("kurs") Kurs kurs){
        kurs.setIdKursu(id);
        kursService.save(kurs);
        return "redirect:/kurs/list";
    }
    
    @RequestMapping(value = "/kurs/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        kursService.delete(id);
        return "redirect:/kurs/list";
    }
}
