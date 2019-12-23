package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Pociag;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PociagRepository extends JpaRepository<Pociag, Long>{
    List <Pociag> findByKursDataKursu(LocalDate dataKursu);
    List <Pociag> findByKursMiejscaPierwszaKlasa(Integer miejscaPierwszaKlasa);
}
