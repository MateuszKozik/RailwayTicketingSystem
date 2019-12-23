package com.kozik.RailwayTicketingSystem.controllers;

import com.kozik.RailwayTicketingSystem.entities.Uprawnienia;
import com.kozik.RailwayTicketingSystem.entities.Uzytkownik;
import com.kozik.RailwayTicketingSystem.services.UprawnieniaService;
import com.kozik.RailwayTicketingSystem.services.UzytkownikService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UzytkownikController {
    @Autowired private UprawnieniaService uprawnieniaService;
    @Autowired private UzytkownikService uzytkownikService;
    
    @RequestMapping(value = "/uzytkownik/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Uzytkownik> uzytkownikList = uzytkownikService.listAll();
        model.addAttribute("uzytkownikList", uzytkownikList);
  
        return "views/uzytkownik/list";
    }
    
    @RequestMapping(value = "/uzytkownik/add", method = RequestMethod.GET)
    public String add(Model model){
        List<Uprawnienia>uprawnieniaList = uprawnieniaService.listAll();
        Uzytkownik uzytkownik = new Uzytkownik(); 
        model.addAttribute("uprawnieniaList", uprawnieniaList);
        model.addAttribute("uzytkownik", uzytkownik);  
        return "views/uzytkownik/add";
    }
    
    @RequestMapping(value = "/uzytkownik/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("uzytkownik") Uzytkownik uzytkownik,
            @RequestParam(name = "uprawnienia", required = false) ArrayList<Uprawnienia>uprawnieniaList){ 
        uzytkownikService.save(uzytkownik, uprawnieniaList);
        return "redirect:/uzytkownik/list";
    }
    
    @RequestMapping(value = "/uzytkownik/edit/{email}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "email") String email){
        Uzytkownik uzytkownik = uzytkownikService.get(email);
        List<Uprawnienia> uprawnieniaList = uzytkownik.getUprawnienia();
        model.addAttribute("uzytkownik",uzytkownik);
        model.addAttribute("uprawnieniaList", uprawnieniaList);
        return "views/uzytkownik/edit";
    }
    
    @RequestMapping(value = "/uzytkownik/edit/{email}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "email") String email,
            @ModelAttribute("uzytkownik") Uzytkownik uzytkownik,
            @ModelAttribute("uprawnieniaList") List<Uprawnienia> uprawnieniaList){
        uzytkownik.setEmail(email);
        uzytkownik.setUprawnienia(uprawnieniaList);
        uzytkownikService.save(uzytkownik, uprawnieniaList);
        return "redirect:/uzytkownik/list";
    }
    
    @RequestMapping(value = "/uzytkownik/delete/{email}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "email") String email){
        uzytkownikService.delete(email);
        return "redirect:/uzytkownik/list";
    }
}
