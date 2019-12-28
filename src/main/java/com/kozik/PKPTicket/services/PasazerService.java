package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Pasazer;
import com.kozik.PKPTicket.repositories.PasazerRepository;
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
    
    public Pasazer getByEmail(String email){
        return pasazerRepository.findByUzytkownikEmail(email);
    }
    
    public boolean isPasazerPresent(String email){
            return pasazerRepository.existsByUzytkownikEmail(email);
    }
}
