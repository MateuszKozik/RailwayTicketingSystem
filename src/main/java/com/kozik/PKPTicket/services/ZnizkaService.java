package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Znizka;
import com.kozik.PKPTicket.repositories.ZnizkaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZnizkaService {
    
    @Autowired private ZnizkaRepository znizkaRepository;
    
    public List<Znizka> listAll(){
        return znizkaRepository.findAll();
    }
    
    public void save(Znizka znizka){
        znizkaRepository.save(znizka);
    }
    
    public Znizka get(Long id){
        return znizkaRepository.findById(id).get();
    }
    
    public void delete(Long id){
        znizkaRepository.deleteById(id);
    }
}
