package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.entities.Maszynista;
import com.kozik.PKPTicket.repositories.KursRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KursService {
    @Autowired
    private KursRepository kursRepository;
    
    public List<Kurs> listAll(){
        return kursRepository.findAll();
    }
    
    public void save(Kurs kurs){
        kursRepository.save(kurs);
    }
    
    public Kurs get(long id){
        return kursRepository.findById(id).get();
    }
    
    public void delete(long id){
        kursRepository.deleteById(id);
    } 
    
    public List<Kurs> findByStacjaPoczatkowa(String stacjaPoczatkowa){
         LocalDateTime date = LocalDateTime.now();
         return kursRepository.findByPociagStacjaPoczatkowaAndDataKursuGreaterThanOrderByDataKursuAsc(stacjaPoczatkowa, date);
    }
    
    public List<Kurs> findByStacjaKoncowa(String stacjaKoncowa){
           LocalDateTime date = LocalDateTime.now();
           return kursRepository.findByPociagStacjaKoncowaAndDataKursuGreaterThanOrderByDataKursuAsc(stacjaKoncowa, date);
    }
    
    public List<Kurs> findByStacje(String stacjaPoczatkowa, String StacjaKoncowa){
          LocalDateTime date = LocalDateTime.now();
          return kursRepository.findByPociagStacjaPoczatkowaAndPociagStacjaKoncowaAndDataKursuGreaterThanOrderByDataKursuAsc(stacjaPoczatkowa, StacjaKoncowa, date);
    }
    
    public List<Kurs> findByDate(){
        LocalDateTime date = LocalDateTime.now();
        return kursRepository.findByDataKursuGreaterThanOrderByDataKursuAsc(date);
    }
    
    public boolean fistClassAvaliable(Kurs kurs) {
        Integer n = kurs.getMiejscaPierwszaKlasa() - kurs.getSprzedanePierwszaKlasa();
        if (n > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean secondClassAvaliable(Kurs kurs) {
        Integer n = kurs.getMiejscaDrugaKlasa() - kurs.getSprzedaneDrugaKlasa();
        if (n > 0) {
            return true;
        } else {
            return false;
        }
    }
    
    public List<Kurs> getByMaszynista(Maszynista maszynista){
        return kursRepository.findByPociagMaszynista(maszynista);
    }
}
