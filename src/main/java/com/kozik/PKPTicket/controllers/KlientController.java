package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.services.PasazerService;
import com.kozik.PKPTicket.services.UzytkownikService;
import com.kozik.PKPTicket.services.AdresService;
import com.kozik.PKPTicket.entities.Pasazer;
import com.kozik.PKPTicket.entities.Adres;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class KlientController {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private AdresService adresService;

    @RequestMapping(value = "/klient/edit", method = RequestMethod.GET)
    public String edit(Model model, Principal principal) {
        String email = principal.getName();
        if (pasazerService.isPasazerPresent(email)) {
            Pasazer pasazer = pasazerService.getByEmail(email);
            model.addAttribute("pasazer", pasazer);
            if (pasazer.getAdres() == null) {
                Adres adres = new Adres();
                model.addAttribute("adres", adres);
            } else {
                Adres adres = adresService.getByPasazer(pasazer.getIdPasazera());
                model.addAttribute("adres", adres);
            }
            return "/views/klient/edit";
        } else {
            Pasazer pasazer = new Pasazer();
            Adres adres = new Adres();
            model.addAttribute("pasazer", pasazer);
            model.addAttribute("adres", adres);
            return "views/klient/edit";
        }
    }
    
    @RequestMapping(value = "/klient/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("pasazer")Pasazer pasazer,
            @ModelAttribute("adres")Adres adres, Principal principal){
        String email = principal.getName();
        pasazer.setUzytkownik(uzytkownikService.get(email));
        adresService.save(adres);
        pasazer.setAdres(adres);
        pasazerService.save(pasazer);
   
        return "redirect:/klient/edit";
    }
}
