package com.kozik.PKPTicket.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
public class Bilet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id_biletu", nullable = false)
    private Long idBiletu;
    
    @Column(name="data_zakupu", nullable = false)
    private LocalDateTime dataZakupu;
    
    @Column(name="klasa", nullable = false)
    private String klasa;
    
    @ManyToOne
    @JoinColumn(name = "id_kursu", nullable = true)
    private Kurs kurs; 
    
    @ManyToOne
    @JoinColumn(name = "id_pasazera", nullable = true)
    private Pasazer pasazer;
    
    @ManyToOne
    @JoinColumn(name = "id_znizki")
    private Znizka znizka;

    public Bilet() {
        this.dataZakupu = LocalDateTime.now();    
    }

    public Bilet(String klasa, Kurs kurs, Pasazer pasazer, Znizka znizka) {
        this.dataZakupu =LocalDateTime.now();
        this.klasa = klasa;
        this.kurs = kurs;
        this.pasazer = pasazer;
        this.znizka = znizka;
    }
    
      public Bilet(String klasa, Kurs kurs, Pasazer pasazer) {
        this.dataZakupu = LocalDateTime.now();
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

    public String getDataZakupu() {
        if (dataZakupu != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String formatDateTime = dataZakupu.format(formatter);
            return formatDateTime;
        }else return "";
    }

    public void setDataZakupu(String dataZakupu) {
        LocalDateTime dateTime = LocalDateTime.parse(dataZakupu,DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.dataZakupu = dateTime;
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