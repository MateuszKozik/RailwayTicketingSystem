package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.ZakupBiletu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZakupBiletuRepository extends JpaRepository<ZakupBiletu, Long>{
    
}
