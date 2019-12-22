package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.ZakupBiletu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZakupBiletuRepository extends JpaRepository<ZakupBiletu, Long>{
    
}
