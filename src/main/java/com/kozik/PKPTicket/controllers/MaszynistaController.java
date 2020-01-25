package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Maszynista;
import com.kozik.PKPTicket.entities.Adres;
import com.kozik.PKPTicket.entities.Uzytkownik;
import com.kozik.PKPTicket.services.AdresService;
import com.kozik.PKPTicket.services.MaszynistaService;
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
public class MaszynistaController {
    @Autowired private MaszynistaService maszynistaService;
    @Autowired private AdresService adresService;
    @Autowired private UzytkownikService uzytkownikService;
    
    @RequestMapping(value = "/maszynista/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Maszynista> maszynistaList = maszynistaService.listAll();
        model.addAttribute("maszynistaList", maszynistaList);
        return "views/maszynista/list";
    }
    
    @RequestMapping(value = "/maszynista/add", method = RequestMethod.GET)
    public String save(Model model){
        List<Adres> adresList = adresService.listAll();
        List<Uzytkownik> uzytkownikList = uzytkownikService.listAll();
        Maszynista maszynista = new Maszynista();
        model.addAttribute("maszynista", maszynista);
        model.addAttribute("adresList", adresList);
        model.addAttribute("uzytkownikList", uzytkownikList);
        return "views/maszynista/add";
    }
    
    @RequestMapping(value = "/maszynista/add", method = RequestMethod.POST)
    public String save(@Valid Maszynista maszynista,BindingResult bindingResult,
            @RequestParam(name = "adres")Adres adres,
            @RequestParam(name = "uzytkownik") Uzytkownik uzytkownik){
        if(bindingResult.hasErrors()){
            return "views/maszynista/add";
        }
        maszynista.setUzytkownik(uzytkownik);
        maszynista.setAdres(adres);
        maszynistaService.save(maszynista);
        return "redirect:/maszynista/list";
    }
    
    @RequestMapping(value = "/maszynista/edit/{id}", method = RequestMethod.GET)
    public String edit(Model model,@PathVariable(name = "id") long id){
       List<Adres> adresList = adresService.listAll();
       List<Uzytkownik> uzytkownikList = uzytkownikService.listAll();
       Maszynista maszynista=maszynistaService.get(id);
       model.addAttribute("maszynista", maszynista);      
       model.addAttribute("adresList", adresList);
       model.addAttribute("uzytkownikList", uzytkownikList);
       return "/views/maszynista/edit";
    }
    
    @RequestMapping(value = "/maszynista/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @Valid Maszynista maszynista, BindingResult bindingResult,
            @RequestParam(name = "adres")Adres adres,  
            @RequestParam(name = "uzytkownik") Uzytkownik uzytkownik){ 
                if(bindingResult.hasErrors()){
                    return "views/maszynista/edit";
                }
        maszynista.setIdMaszynisty(id);
        maszynista.setAdres(adres);
        maszynista.setUzytkownik(uzytkownik);
        maszynistaService.save(maszynista);
        return "redirect:/maszynista/list";
    }
    
    @RequestMapping(value = "/maszynista/delete/{id}", method = RequestMethod.GET)
    public String delete(Model model,@PathVariable(name = "id") long id){     
       maszynistaService.delete(id);
       return "redirect:/maszynista/list";
    }
    
}
