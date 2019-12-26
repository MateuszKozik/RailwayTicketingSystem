package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Pasazer;
import com.kozik.PKPTicket.entities.Znizka;
import com.kozik.PKPTicket.services.BiletService;
import com.kozik.PKPTicket.services.KursService;
import com.kozik.PKPTicket.services.PasazerService;
import com.kozik.PKPTicket.services.ZnizkaService;
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
public class BiletController {
    @Autowired private BiletService biletService;
    @Autowired private KursService kursService;
    @Autowired private PasazerService pasazerService;
    @Autowired private ZnizkaService znizkaService;
    
    @RequestMapping(value = "/bilet/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<Bilet> biletList = biletService.listAll();
        model.addAttribute("biletList", biletList);
        return "views/bilet/list";
    }
    
    @RequestMapping(value = "/bilet/add", method = RequestMethod.GET)
    public String add(Model model){
        List<Znizka> znizkaList = znizkaService.listAll();
        List<Kurs> kursList = kursService.listAll();
        List<Pasazer> pasazerList = pasazerService.listAll();
        Bilet bilet = new Bilet();
        model.addAttribute("bilet", bilet);
        model.addAttribute("znizkaList", znizkaList);
        model.addAttribute("kursList", kursList);
        model.addAttribute("pasazerList", pasazerList);
        return "views/bilet/add";
    }
    
    @RequestMapping(value = "/bilet/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("bilet") Bilet bilet,
            @RequestParam("znizka") Znizka znizka,
            @RequestParam("kurs") Kurs kurs,
            @RequestParam("pasazer") Pasazer pasazer){
        bilet.setZnizka(znizka);
        bilet.setKurs(kurs);
        bilet.setPasazer(pasazer);
        biletService.save(bilet);
        return "redirect:/bilet/list";
    }
    
    @RequestMapping(value = "/bilet/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(name = "id") long id, Model model){
        List<Znizka> znizkaList = znizkaService.listAll();
        List<Kurs> kursList = kursService.listAll();
        List<Pasazer> pasazerList = pasazerService.listAll();
        Bilet bilet = biletService.get(id);
        model.addAttribute("bilet", bilet);
        model.addAttribute("znizkaList", znizkaList);
        model.addAttribute("kursList", kursList);
        model.addAttribute("pasazerList", pasazerList);
        return "/views/bilet/edit";
    }
    
    @RequestMapping(value = "/bilet/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @ModelAttribute("bilet") Bilet bilet,
            @RequestParam("znizka") Znizka znizka,
            @RequestParam("kurs") Kurs kurs,
            @RequestParam("pasazer") Pasazer pasazer){
        bilet.setZnizka(znizka);
        bilet.setKurs(kurs);
        bilet.setPasazer(pasazer);
        bilet.setIdBiletu(id);
        biletService.save(bilet);
        return "redirect:/bilet/list";
    }
    
    @RequestMapping(value = "/bilet/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        biletService.delete(id);
        return "redirect:/bilet/list";
    }
}
