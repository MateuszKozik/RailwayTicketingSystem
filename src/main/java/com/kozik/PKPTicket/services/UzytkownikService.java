package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Uprawnienia;
import com.kozik.PKPTicket.entities.Uzytkownik;
import com.kozik.PKPTicket.repositories.UzytkownikRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UzytkownikService {
    
    @Autowired private UzytkownikRepository uzytkownikRepository;
    
    public List<Uzytkownik> listAll(){
        return uzytkownikRepository.findAll();
    }
    
    public void save(Uzytkownik uzytkownik, List<Uprawnienia> uprawnienia){
        uzytkownik.setUprawnienia(uprawnienia);
        uzytkownikRepository.save(uzytkownik);
    }
    
    public Uzytkownik get(String email){
        return uzytkownikRepository.findById(email).get();
    }
    
    public void delete(String email){
       uzytkownikRepository.deleteById(email);
    }
}
