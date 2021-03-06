package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.services.PasazerService;
import com.kozik.PKPTicket.services.UzytkownikService;
import com.kozik.PKPTicket.services.ZnizkaService;
import com.kozik.PKPTicket.services.KursService;
import com.kozik.PKPTicket.services.BiletService;
import com.kozik.PKPTicket.services.AdresService;
import com.kozik.PKPTicket.entities.Pasazer;
import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.entities.Adres;
import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Znizka;
import java.security.Principal;
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
public class KlientController {

    @Autowired
    private UzytkownikService uzytkownikService;
    @Autowired
    private PasazerService pasazerService;
    @Autowired
    private AdresService adresService;
    @Autowired
    private KursService kursService;
    @Autowired
    private ZnizkaService znizkaService;
    @Autowired
    private BiletService biletService;

    @RequestMapping(value = "/klient/profile", method = RequestMethod.GET)
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
            return "/views/klient/profile";
        } else {
            Pasazer pasazer = new Pasazer();
            Adres adres = new Adres();
            model.addAttribute("pasazer", pasazer);
            model.addAttribute("adres", adres);
            return "views/klient/profile";
        }
    }

    @RequestMapping(value = "/klient/profile", method = RequestMethod.POST)
    public String edit(@Valid Pasazer pasazer,BindingResult bindingResult,
            @Valid Adres adres, BindingResult bindingResult2, Principal principal) {
                if(bindingResult.hasErrors()){
                    return "views/klient/profile";
                }
                if(bindingResult2.hasErrors()){
                    return "views/klient/profile";
                }
        String email = principal.getName();
        pasazer.setUzytkownik(uzytkownikService.get(email));
        adresService.save(adres);
        pasazer.setAdres(adres);
        pasazerService.save(pasazer);

        return "redirect:/klient/profile";
    }

    @RequestMapping(value = "/klient/zakup/{id}", method = RequestMethod.GET)
    public String buy(Model model, @PathVariable(name = "id") long id) {
        Kurs kurs = kursService.get(id);
        List<Znizka> znizkaList = znizkaService.listAll();
        model.addAttribute("kurs", kurs);
        model.addAttribute("znizkaList", znizkaList);
        return "views/klient/buy";
    }

    @RequestMapping(value = "/klient/zakup/{id}", method = RequestMethod.POST)
    public String buy(@PathVariable(name = "id") long id,
            @RequestParam(name = "znizka", required = false) Znizka znizka,
            @RequestParam(name = "klasa") String klasa,
            Principal principal, Model model) {
        String email = principal.getName();

        if (pasazerService.isPasazerPresent(email)) {
            Pasazer pasazer = pasazerService.getByEmail(principal.getName());
            Kurs kurs = kursService.get(id);
            if ((Integer.parseInt(klasa) == 1) && kursService.fistClassAvaliable(kurs)) {
                Bilet bilet = new Bilet();
                bilet.setKlasa(klasa);
                bilet.setKurs(kurs);
                bilet.setZnizka(znizka);
                bilet.setPasazer(pasazer);
                biletService.save(bilet);
                model.addAttribute("transactionSuccess", true);
                return "views/klient/transaction";
            } else if ((Integer.parseInt(klasa) == 2) && kursService.secondClassAvaliable(kurs)) {
                Bilet bilet = new Bilet();
                bilet.setKlasa(klasa);
                bilet.setKurs(kurs);
                bilet.setZnizka(znizka);
                bilet.setPasazer(pasazer);
                biletService.save(bilet);
                model.addAttribute("transactionSuccess", true);
                return "views/klient/transaction";
            } else {
                model.addAttribute("transactionFalse", true);
                return "views/klient/transaction";
            }
        } else {
            model.addAttribute("userDataFalse", true);
            return "/views/klient/transaction";
        }
    }

    @RequestMapping(value = "/klient/tickets", method = RequestMethod.GET)
    public String tickets(Model model, Principal principal) {
        String email = principal.getName();
        if (pasazerService.isPasazerPresent(email)) {
            Pasazer pasazer = pasazerService.getByEmail(email);
            List<Bilet> biletList = biletService.getByPasazer(pasazer);
            if (biletList.isEmpty()) {
                model.addAttribute("empty", true);
                return "/views/klient/tickets";
            } else {
                model.addAttribute("biletList", biletList);
                return "/views/klient/tickets";
            }
        } else {
            model.addAttribute("empty", true);
            return "/views/klient/tickets";
        }
    }
}
