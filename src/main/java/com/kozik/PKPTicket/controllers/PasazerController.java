package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Adres;
import com.kozik.PKPTicket.entities.Pasazer;
import com.kozik.PKPTicket.entities.Uzytkownik;
import com.kozik.PKPTicket.services.PasazerService;
import com.kozik.PKPTicket.services.AdresService;
import com.kozik.PKPTicket.services.UzytkownikService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasazerController {
    
    @Autowired private PasazerService pasazerService;
    @Autowired private AdresService adresService;
    @Autowired private UzytkownikService uzytkownikService;
    
    @RequestMapping(value = "/pasazer/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Pasazer> pasazerList = pasazerService.listAll();
        model.addAttribute("pasazerList", pasazerList);
        
        return "views/pasazer/list";
    }
    
    @RequestMapping(value = "/pasazer/add", method = RequestMethod.GET)
    public String add(Model model){
        List<Adres> adresList = adresService.listAll();
        List<Uzytkownik> uzytkownikList = uzytkownikService.listAll();
        Pasazer pasazer = new Pasazer();
        model.addAttribute("pasazer", pasazer);
        model.addAttribute("adresList", adresList);
        model.addAttribute("uzytkownikList", uzytkownikList);
        return "views/pasazer/add";
    }
    
    @RequestMapping(value = "/pasazer/add", method = RequestMethod.POST)
    public String add(@Valid Pasazer pasazer, BindingResult bindingResult,
            @RequestParam(name = "adres")Adres adres,
            @RequestParam(name = "uzytkownik") Uzytkownik uzytkownik){
                if(bindingResult.hasErrors()){
                    return "views/pasazer/add";
                }
        pasazer.setAdres(adres);
        pasazer.setUzytkownik(uzytkownik);
        pasazerService.save(pasazer);
        return "redirect:/pasazer/list";
    }
    
    @RequestMapping(value = "/pasazer/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "id") long id){
        List<Adres> adresList = adresService.listAll();
        List<Uzytkownik> uzytkownikList = uzytkownikService.listAll();
        Pasazer pasazer = pasazerService.get(id);
        model.addAttribute("adresList", adresList);
        model.addAttribute("uzytkownikList", uzytkownikList);
        model.addAttribute("pasazer", pasazer);      
        return "/views/pasazer/edit";
    }
    
     @RequestMapping(value = "/pasazer/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
     @Valid Pasazer pasazer, BindingResult bindingResult,
            @RequestParam(name = "adres")Adres adres,
            @RequestParam(name = "uzytkownik") Uzytkownik uzytkownik){ 
                if(bindingResult.hasErrors()){
                    return "views/pasazer/edit";
                }
        pasazer.setIdPasazera(id);
        pasazer.setAdres(adres);
        pasazer.setUzytkownik(uzytkownik);
        pasazerService.save(pasazer);
        return "redirect:/pasazer/list";
    }
    
    @RequestMapping(value = "/pasazer/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        pasazerService.delete(id);
        return "redirect:/pasazer/list";
    }
    
}
