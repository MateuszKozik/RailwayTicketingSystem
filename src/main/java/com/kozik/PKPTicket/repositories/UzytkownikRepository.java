package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Uzytkownik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UzytkownikRepository extends JpaRepository<Uzytkownik, String>{
    
}
