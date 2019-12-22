package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.Adres;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepository extends JpaRepository<Adres, Long>{

    public List<Adres> deleteByUlicaAndNumerDomu(String ulica, Integer nrDomu);
    public List<Adres> findByUlicaAndNumerDomu(String ulica, Integer nrDomu);
    
}
