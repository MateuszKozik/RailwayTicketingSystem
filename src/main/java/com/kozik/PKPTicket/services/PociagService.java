package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Pociag;
import com.kozik.PKPTicket.repositories.PociagRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PociagService {

    @Autowired 
    private PociagRepository pociagRepository;
    
    public List<Pociag> listAll(){
        return pociagRepository.findAll();
    }
    
    public void save(Pociag pociag){
        pociagRepository.save(pociag);
    }
    
    public Pociag get(long id){
        return pociagRepository.findById(id).get();
    }
    
    public void delete(long id){
        pociagRepository.deleteById(id);
    }
}
