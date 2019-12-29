package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Adres;
import com.kozik.PKPTicket.services.KursService;
import com.kozik.PKPTicket.services.UzytkownikService;
import com.kozik.PKPTicket.entities.Maszynista;
import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Adres;
import com.kozik.PKPTicket.services.AdresService;
import com.kozik.PKPTicket.services.MaszynistaService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ObslugaController {

    @Autowired private KursService kursService;
    @Autowired private MaszynistaService maszynistaService;
    @Autowired private AdresService adresService;
    @Autowired private UzytkownikService uzytkownikService;

    @RequestMapping(value = "/obsluga/courses", method = RequestMethod.GET)
    public String get(Principal principal, Model model) {
        String email = principal.getName();
        if (maszynistaService.isMaszynistaPresent(email)) {
            Maszynista maszynista = maszynistaService.getByEmail(email);
            List<Kurs> kursList = kursService.getByMaszynista(maszynista);
            if (kursList.isEmpty()) {
                model.addAttribute("empty", true);
                return "views/obsluga/courses";
            } else {
                model.addAttribute("kursList", kursList);
                return "views/obsluga/courses";
            }

        } else {
            model.addAttribute("empty", true);
        }
        return "views/obsluga/courses";
    }
    
       @RequestMapping(value = "/obsluga/profile", method = RequestMethod.GET)
    public String edit(Model model, Principal principal) {
        String email = principal.getName();
        if (maszynistaService.isMaszynistaPresent(email)) {
            Maszynista maszynista = maszynistaService.getByEmail(email);
            model.addAttribute("maszynista", maszynista);
            if (maszynista.getAdres() == null) {
                Adres adres = new Adres();
                model.addAttribute("adres", adres);
            } else {
                Adres adres = adresService.getByMaszynista(maszynista.getIdMaszynisty());
                model.addAttribute("adres", adres);
            }
            return "/views/obsluga/profile";
        } else {
            Maszynista maszynista = new Maszynista();
            Adres adres = new Adres();
            model.addAttribute("maszynista", maszynista);
            model.addAttribute("adres", adres);
            return "views/obsluga/profile";
        }
    }

    @RequestMapping(value = "/obsluga/profile", method = RequestMethod.POST)
    public String edit(@ModelAttribute("maszynista") Maszynista maszynista,
            @ModelAttribute("adres") Adres adres, Principal principal) {
        String email = principal.getName();
        maszynista.setUzytkownik(uzytkownikService.get(email));
        adresService.save(adres);
        maszynista.setAdres(adres);
        maszynistaService.save(maszynista);

        return "redirect:/obsluga/profile";
    }
}
