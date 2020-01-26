package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.entities.Pasazer;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiletRepository extends JpaRepository<Bilet, Long>{
    List<Bilet> findByPasazerOrderByDataZakupuDesc(Pasazer pasazer);
    List<Bilet> findByDataZakupuAfter(LocalDateTime date);
}
