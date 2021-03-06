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
        LocalDateTime dateTime = LocalDateTime.now();
         DateTimeFormatter year = DateTimeFormatter.ofPattern("yyyy-MM-dd");
          DateTimeFormatter hour = DateTimeFormatter.ofPattern("HH:mm");
          String yearString = dateTime.format(year);
          String hourString = dateTime.format(hour);
          String format = yearString + "T" + hourString;
        this.dataZakupu = LocalDateTime.parse(format,DateTimeFormatter.ISO_LOCAL_DATE_TIME);;    
    }

    public Long getIdBiletu() {
        return idBiletu;
    }

    public void setIdBiletu(Long idBiletu) {
        this.idBiletu = idBiletu;
    }

    public String getDataZakupu() {
        if (dataZakupu != null) {
            return dataZakupu.toString();
        } else {
            return "";
        }
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