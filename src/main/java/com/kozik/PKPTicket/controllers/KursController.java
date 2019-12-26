package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Pociag;
import com.kozik.PKPTicket.services.KursService;
import com.kozik.PKPTicket.services.PociagService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KursController {
    @Autowired private KursService kursService;
    @Autowired private PociagService pociagService;
            
    @RequestMapping(value = "/kurs/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Kurs> kursList = kursService.listAll();
        model.addAttribute("kursList", kursList);
        return "views/kurs/list";
    }
    
    @RequestMapping(value = "/kurs/add", method = RequestMethod.GET)
    public String add(Model model){
        List<Pociag> pociagList = pociagService.listAll();
        Kurs kurs = new Kurs();
        model.addAttribute("kurs", kurs);
        model.addAttribute("pociagList", pociagList);
        return "views/kurs/add";
    }
    
    @RequestMapping(value = "/kurs/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("kurs") Kurs kurs,
            @RequestParam("pociag") Pociag pociag){
       kurs.setPociag(pociag);
       kursService.save(kurs);
       return "redirect:/kurs/list";
    }
    
    @RequestMapping(value = "/kurs/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable(name = "id") long id) {
        List<Pociag> pociagList = pociagService.listAll();
        Kurs kurs = kursService.get(id);
        model.addAttribute("kurs", kurs);
        model.addAttribute("pociagList", pociagList);
        return "views/kurs/edit";
    }
    
    @RequestMapping(value = "/kurs/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @ModelAttribute("kurs") Kurs kurs,
            @RequestParam("pociag") Pociag pociag){
        kurs.setIdKursu(id);
        kurs.setPociag(pociag);
        kursService.save(kurs);
        return "redirect:/kurs/list";
    }
    
    @RequestMapping(value = "/kurs/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        kursService.delete(id);
        return "redirect:/kurs/list";
    }
}
