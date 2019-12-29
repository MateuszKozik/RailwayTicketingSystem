package com.kozik.PKPTicket.repositories;

import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Maszynista;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KursRepository extends JpaRepository<Kurs, Long> {
    List <Kurs> findByMiejscaPierwszaKlasaLessThan(int miejsca);
    List <Kurs> findByPociagStacjaPoczatkowaAndDataKursuGreaterThanOrderByDataKursuAsc( String stacjaPoczatkowa,LocalDateTime dataKursu);
    List <Kurs> findByPociagStacjaKoncowaAndDataKursuGreaterThanOrderByDataKursuAsc( String stacjaKoncowa,LocalDateTime dataKursu);
    List <Kurs> findByPociagStacjaPoczatkowaAndPociagStacjaKoncowaAndDataKursuGreaterThanOrderByDataKursuAsc(String stacjaPoczatkowa, String stacjaKoncowa, LocalDateTime dataKursu);
    List <Kurs> findByDataKursuGreaterThanOrderByDataKursuAsc(LocalDateTime dataKursu);
    long countByDataKursu(LocalDate dataKursu);
    List<Kurs> findByPociagMaszynista(Maszynista maszynista);
}