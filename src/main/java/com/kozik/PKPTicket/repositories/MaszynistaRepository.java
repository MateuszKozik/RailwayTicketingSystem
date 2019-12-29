package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Maszynista;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaszynistaRepository extends JpaRepository<Maszynista, Long>{
    List<Maszynista> findByPociagNazwa(String nazwa);
    List <Maszynista> findByPociagKurs(Kurs kurs);
    boolean existsByUzytkownikEmail(String email);
    Maszynista findByUzytkownikEmail(String email);
}


