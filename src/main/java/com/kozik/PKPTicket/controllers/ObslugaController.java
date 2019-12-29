package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.services.KursService;
import com.kozik.PKPTicket.entities.Maszynista;
import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.services.MaszynistaService;
import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ObslugaController {

    @Autowired
    KursService kursService;
    @Autowired
    MaszynistaService maszynistaService;

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
}
