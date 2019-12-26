package com.kozik.PKPTicket.services;

import com.kozik.PKPTicket.entities.Bilet;
import com.kozik.PKPTicket.entities.Kurs;
import com.kozik.PKPTicket.repositories.BiletRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BiletService {
    @Autowired
    private BiletRepository biletRepository;
    @Autowired
    private KursService kursService;
    
    public List<Bilet> listAll(){
        return biletRepository.findAll();
    }
    
    public void edit(Bilet bilet){
        biletRepository.save(bilet);
    }
    
    public void save(Bilet bilet) {
        Integer klasa = Integer.parseInt(bilet.getKlasa());
        Kurs kurs = bilet.getKurs();
        if (klasa == 1) {
            if (kurs.getSprzedanePierwszaKlasa() <= kurs.getMiejscaPierwszaKlasa()) {
                kurs.setSprzedanePierwszaKlasa(kurs.getSprzedanePierwszaKlasa() + 1);
                kursService.save(kurs);
                biletRepository.save(bilet);
            }

        }
        if (klasa == 2) {
            if (kurs.getSprzedaneDrugaKlasa() <= kurs.getMiejscaDrugaKlasa()) {
                kurs.setSprzedaneDrugaKlasa(kurs.getSprzedaneDrugaKlasa() + 1);
                kursService.save(kurs);
                biletRepository.save(bilet);
            }
        }
    }

    public Bilet get(long id){
        return biletRepository.findById(id).get();
    }
    
    public void delete(long id){
        biletRepository.deleteById(id);
    }
    
}
