package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.Znizka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZnizkaRepository extends JpaRepository<Znizka, Long>{
    
}