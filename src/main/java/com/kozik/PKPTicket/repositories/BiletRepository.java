package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Bilet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiletRepository extends JpaRepository<Bilet, Long>{
    
}
