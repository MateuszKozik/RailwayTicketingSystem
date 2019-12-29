package com.kozik.PKPTicket.controllers;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.kozik.PKPTicket.entities.Pasazer;
import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.services.BiletService;
import com.kozik.PKPTicket.services.PasazerService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
    
    @Autowired PasazerService pasazerSerivce;
    @Autowired BiletService biletService;
    
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String showProfile(Principal principal, Model model){
        String email = principal.getName();
        if(pasazerSerivce.isPasazerPresent(email)){
           Pasazer pasazer = pasazerSerivce.getByEmail(email);
           List<Bilet> AllBiletList = biletService.getByPasazer(pasazer);
           List<Bilet> biletList= new ArrayList<Bilet>();
           if(AllBiletList.size()>=3){
               biletList.add(AllBiletList.get(0));
               biletList.add(AllBiletList.get(1));
               biletList.add(AllBiletList.get(2));
               model.addAttribute("biletList", biletList);
           }else if(AllBiletList.size()>=2){
               biletList.add(AllBiletList.get(0));
               biletList.add(AllBiletList.get(1));
               model.addAttribute("biletList", biletList);
           }else if(AllBiletList.size()== 1){
               biletList.add(AllBiletList.get(0));
               model.addAttribute("biletList", biletList);
           }else if(AllBiletList.size() == 0){
               model.addAttribute("notBuy", true); 
           }   
        }else{
           model.addAttribute("notBuy", true); 
        }    
        return "/views/home";
    }
}
