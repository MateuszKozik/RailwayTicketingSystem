package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Znizka;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZnizkaRepository extends JpaRepository<Znizka, Long>{
    
}