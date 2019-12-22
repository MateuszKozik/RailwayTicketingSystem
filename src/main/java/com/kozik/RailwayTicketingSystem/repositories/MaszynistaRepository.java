package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.Kurs;
import com.kozik.RailwayTicketingSystem.entities.Maszynista;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaszynistaRepository extends JpaRepository<Maszynista, Long>{
    List<Maszynista> findByPociagNazwa(String nazwa);
    List <Maszynista> findByPociagKurs(Kurs kurs);
}


