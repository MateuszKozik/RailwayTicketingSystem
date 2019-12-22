package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UzytkownikRepository extends JpaRepository<Uzytkownik, Long>{
    
}
