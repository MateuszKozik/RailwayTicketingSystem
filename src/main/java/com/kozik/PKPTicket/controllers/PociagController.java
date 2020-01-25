package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Pociag;
import com.kozik.PKPTicket.entities.Maszynista;
import com.kozik.PKPTicket.services.MaszynistaService;
import com.kozik.PKPTicket.services.PociagService;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PociagController {
    
    @Autowired private PociagService pociagService;
    @Autowired private MaszynistaService maszynistaService;
    
    @RequestMapping(value = "/pociag/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Pociag> pociagList = pociagService.listAll();
        model.addAttribute("pociagList", pociagList);
        return "views/pociag/list";
    }
    
    @RequestMapping(value = "/pociag/add", method = RequestMethod.GET)
    public String add(Model model){
        List<Maszynista> maszynistaList = maszynistaService.listAll();
        Pociag pociag = new Pociag();
        model.addAttribute("pociag", pociag);
        model.addAttribute("maszynistaList", maszynistaList);
        return "views/pociag/add";
    }
    
    @RequestMapping(value = "/pociag/add", method = RequestMethod.POST)
    public String add(@Valid Pociag pociag, BindingResult bindingResult,
            @RequestParam("maszynista") Maszynista maszynista){
                if(bindingResult.hasErrors()){
                    return "views/pociag/add";
                }
        pociag.setMaszynista(maszynista);
        pociagService.save(pociag);
        return "redirect:/pociag/list";
    }
    
    @RequestMapping(value = "/pociag/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model, @PathVariable(name = "id") long id){
        List<Maszynista> maszynistaList = maszynistaService.listAll();
        Pociag pociag = pociagService.get(id);
        model.addAttribute("pociag", pociag);
        model.addAttribute("maszynistaList", maszynistaList);
        return "views/pociag/edit";
    }
    
    @RequestMapping(value = "/pociag/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @Valid Pociag pociag, BindingResult bindingResult,
            @RequestParam("maszynista") Maszynista maszynista){
                if(bindingResult.hasErrors()){
                    return "views/pociag/edit";
                }
        pociag.setIdPociagu(id);
        pociag.setMaszynista(maszynista);
        pociagService.save(pociag);
        return "redirect:/pociag/list";
    }
    
    @RequestMapping(value = "/pociag/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        pociagService.delete(id);
        return "redirect:/pociag/list";
    }
}
