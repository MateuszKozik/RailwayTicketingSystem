package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.Kurs;
import com.kozik.RailwayTicketingSystem.entities.Pasazer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasazerRepository extends JpaRepository<Pasazer, Long>{
    List<Pasazer> findByZakupBiletuKurs(Kurs kurs);
}
