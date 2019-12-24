package com.kozik.PKPTicket.entities;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Zakup_biletu")
public class ZakupBiletu {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_biletu", nullable = false)
    private Long idBiletu;
    
    @Column(name="data_zakupu", nullable = false)
    private LocalDate dataZakupu;
    
    @Column(name="klasa", nullable = false)
    private String klasa;
    
    @ManyToOne
    @JoinColumn(name = "id_kursu", nullable = false)
    private Kurs kurs; 
    
    @ManyToOne
    @JoinColumn(name = "id_pasazera", nullable = false)
    private Pasazer pasazer;
    
    @ManyToOne
    @JoinColumn(name = "id_znizki")
    private Znizka znizka;

    public ZakupBiletu() {}

    public ZakupBiletu(String klasa, Kurs kurs, Pasazer pasazer, Znizka znizka) {
        this.dataZakupu = LocalDate.now();
        this.klasa = klasa;
        this.kurs = kurs;
        this.pasazer = pasazer;
        this.znizka = znizka;
    }
    
      public ZakupBiletu(String klasa, Kurs kurs, Pasazer pasazer) {
        this.dataZakupu = LocalDate.now();
        this.klasa = klasa;
        this.kurs = kurs;
        this.pasazer = pasazer;
    }

    public Long getIdBiletu() {
        return idBiletu;
    }

    public void setIdBiletu(Long idBiletu) {
        this.idBiletu = idBiletu;
    }

    public LocalDate getDataZakupu() {
        return dataZakupu;
    }

    public void setDataZakupu(LocalDate dataZakupu) {
        this.dataZakupu = dataZakupu;
    }

    public String getKlasa() {
        return klasa;
    }

    public void setKlasa(String klasa) {
        this.klasa = klasa;
    }

    public Kurs getKurs() {
        return kurs;
    }

    public void setKurs(Kurs kurs) {
        this.kurs = kurs;
    }

    public Pasazer getPasazer() {
        return pasazer;
    }

    public void setPasazer(Pasazer pasazer) {
        this.pasazer = pasazer;
    }

    public Znizka getZnizka() {
        return znizka;
    }

    public void setZnizka(Znizka znizka) {
        this.znizka = znizka;
    }
  
    @Override
    public String toString() {
        return String.format("[%s - %s - %s]", idBiletu,dataZakupu,klasa);
    }
}