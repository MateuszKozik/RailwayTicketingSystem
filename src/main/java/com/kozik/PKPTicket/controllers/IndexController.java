package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.services.KursService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private KursService kursService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage(Model model) {
        // LocalDateTime  date= LocalDateTime.now();
        // List<Kurs> listKurs = kursRepository.findByPociagStacjaPoczatkowaAndDataKursuGreaterThan("Kraków", date);
        return "/index";
    }

    @RequestMapping(value = "/listKurs", method = RequestMethod.POST)
    public String showIndexPage(Model model,
            @RequestParam(name = "stacjaPoczatkowa", required = false) String stacjaPoczatkowa,
            @RequestParam(name = "stacjaKoncowa", required = false) String stacjaKoncowa) {
        List<Kurs> kursList = null;
        if (stacjaPoczatkowa != "" && stacjaKoncowa != "") {
            kursList = kursService.findByStacje(stacjaPoczatkowa, stacjaKoncowa); 
            model.addAttribute("kursList", kursList);
            return "/views/listKurs";
        } else if (stacjaPoczatkowa != "") {
            kursList = kursService.findByStacjaPoczatkowa(stacjaPoczatkowa);
            model.addAttribute("kursList", kursList);
            return "/views/listKurs";
        } else if (stacjaKoncowa != "") {
            kursList = kursService.findByStacjaKoncowa(stacjaKoncowa);
            model.addAttribute("kursList", kursList);
            return "/views/listKurs";
        } else return "redirect:/";
    }

    @GetMapping("/home")
    public String homeIndexPage() {
        return "/index";
    }
}
