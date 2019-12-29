package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Maszynista;
import com.kozik.PKPTicket.repositories.MaszynistaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaszynistaService {
    @Autowired private MaszynistaRepository maszynistaRepository;
    
    public List<Maszynista> listAll(){
        return maszynistaRepository.findAll();
    }
    
    public void save(Maszynista maszynista){
        maszynistaRepository.save(maszynista);
    }
    
    public Maszynista get(Long id){
        return maszynistaRepository.findById(id).get();
    }
    
    public void delete(Long id){
        maszynistaRepository.deleteById(id);
    } 

    public boolean isMaszynistaPresent(String email) {
        return maszynistaRepository.existsByUzytkownikEmail(email);
    }

    public Maszynista getByEmail(String email) {
        return maszynistaRepository.findByUzytkownikEmail(email);
    }
}
