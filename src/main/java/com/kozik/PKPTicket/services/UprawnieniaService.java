package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Uprawnienia;
import com.kozik.PKPTicket.repositories.UprawnieniaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UprawnieniaService {
    @Autowired
    private UprawnieniaRepository uprawnieniaRepository;
    
    public List<Uprawnienia> listAll(){
        return uprawnieniaRepository.findAll();
    }
}
