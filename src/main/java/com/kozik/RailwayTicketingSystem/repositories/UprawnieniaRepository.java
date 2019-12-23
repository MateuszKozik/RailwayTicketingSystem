package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.Uprawnienia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UprawnieniaRepository extends JpaRepository<Uprawnienia, String>{
    
}
