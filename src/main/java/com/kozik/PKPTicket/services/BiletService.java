package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.repositories.BiletRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiletService {
    @Autowired
    private BiletRepository biletRepository;
    
    public List<Bilet> listAll(){
        return biletRepository.findAll();
    }
    
    public void save(Bilet bilet){
        biletRepository.save(bilet);
    }
    
    public Bilet get(long id){
        return biletRepository.findById(id).get();
    }
    
    public void delete(long id){
        biletRepository.deleteById(id);
    }
    
}
