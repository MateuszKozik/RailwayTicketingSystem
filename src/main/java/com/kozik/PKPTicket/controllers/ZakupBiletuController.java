package com.kozik.PKPTicket.controllers;

import com.kozik.PKPTicket.entities.ZakupBiletu;
import com.kozik.PKPTicket.services.ZakupBiletuService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ZakupBiletuController {
    @Autowired
    private ZakupBiletuService zakupBiletuService;
    
    @RequestMapping(value = "/zakupBiletu/list", method = RequestMethod.GET)
    public String getAll(Model model){
        List<ZakupBiletu> zakupBiletuList = zakupBiletuService.listAll();
        model.addAttribute("zakupBiletuList", zakupBiletuList);
        return "views/zakupBiletu/list";
    }
    
    @RequestMapping(value = "/zakupBiletu/add", method = RequestMethod.GET)
    public String add(Model model){
        ZakupBiletu zakupBiletu = new ZakupBiletu();
        model.addAttribute("zakupBiletu", zakupBiletu);
        return "views/zakupBiletu/add";
    }
    
    @RequestMapping(value = "/zakupBiletu/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("zakupBiletu") ZakupBiletu zakupBiletu){
        zakupBiletuService.save(zakupBiletu);
        return "redirect:/zakupBiletu/list";
    }
    
    @RequestMapping(value = "/zakupBiletu/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable(name = "id") long id, Model model){
        ZakupBiletu zakupBiletu = zakupBiletuService.get(id);
        model.addAttribute("zakupBiletu", zakupBiletu);
        return "/views/zakupBiletu/edit";
    }
    
    @RequestMapping(value = "/zakupBiletu/edit/{id}", method = RequestMethod.POST)
    public String edit(@PathVariable(name = "id") long id,
            @ModelAttribute("zakupBiletu") ZakupBiletu zakupBiletu){
        zakupBiletu.setIdBiletu(id);
        zakupBiletuService.save(zakupBiletu);
        return "redirect:/zakupBiletu/list";
    }
    
    @RequestMapping(value = "/zakupBiletu/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable(name = "id") long id){
        zakupBiletuService.delete(id);
        return "redirect:/zakupBiletu/list";
    }
}
