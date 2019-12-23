package com.kozik.RailwayTicketingSystem.services;

import com.kozik.RailwayTicketingSystem.entities.Pasazer;
import com.kozik.RailwayTicketingSystem.repositories.PasazerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasazerService {
    
    @Autowired
    private PasazerRepository pasazerRepository;
    
    public List<Pasazer> listAll(){
        return pasazerRepository.findAll();
    }
    
    public void save(Pasazer pasazer){
        pasazerRepository.save(pasazer);
    }
    
    public Pasazer get(long id){
        return pasazerRepository.findById(id).get();
    }
    
    public void delete(long id){
        pasazerRepository.deleteById(id);
    }
}
