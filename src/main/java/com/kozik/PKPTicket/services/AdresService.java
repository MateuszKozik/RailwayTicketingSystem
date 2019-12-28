package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Adres;
import com.kozik.PKPTicket.repositories.AdresRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdresService {
    @Autowired
    private AdresRepository adresRepository;
    
    public List<Adres> listAll(){
       return adresRepository.findAll();
    }
    
    public void save(Adres adres){
        adresRepository.save(adres);
    }
    
    public Adres get(Long id){
        return adresRepository.findById(id).get();
    }
    
    public void delete(Long id){
        adresRepository.deleteById(id);
    }
    
    public Adres getByPasazer(Long id){
        return adresRepository.findByPasazerIdPasazera(id);
    }
}