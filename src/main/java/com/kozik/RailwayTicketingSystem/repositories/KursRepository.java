package com.kozik.RailwayTicketingSystem.repositories;

import com.kozik.RailwayTicketingSystem.entities.Kurs;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KursRepository extends JpaRepository<Kurs, Long> {
    List <Kurs> findByMiejscaPierwszaKlasaLessThan(int miejsca);
    List <Kurs> findByPociagStacjaPoczatkowa(String stacjaPoczatkowa);
    long countByDataKursu(LocalDate dataKursu);
}