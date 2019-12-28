package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Pasazer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasazerRepository extends JpaRepository<Pasazer, Long>{
    List<Pasazer> findByBiletKurs(Kurs kurs);
    Pasazer findByUzytkownikEmail(String email);
    boolean existsByUzytkownikEmail(String email);
}
