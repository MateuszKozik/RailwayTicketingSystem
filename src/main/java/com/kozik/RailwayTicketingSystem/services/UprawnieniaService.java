package com.kozik.RailwayTicketingSystem.services;

import com.kozik.RailwayTicketingSystem.entities.Uprawnienia;
import com.kozik.RailwayTicketingSystem.repositories.UprawnieniaRepository;
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
