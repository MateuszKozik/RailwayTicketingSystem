package com.kozik.RailwayTicketingSystem.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "kurs")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_kursu", nullable = false)
    private Long idKursu;

    @Column(name = "data_kursu", nullable = false)
    private LocalDate dataKursu;

    @Column(name = "miejsca_pierwsza_klasa", nullable = false)
    private Integer miejscaPierwszaKlasa;

    @Column(name = "miejsca_druga_klasa", nullable = false)
    private Integer miejscaDrugaKlasa;

    @Column(name = "sprzedana_pierwsza_klasa", nullable = false)
    private Integer sprzedanePierwszaKlasa;

    @Column(name = "sprzedana_druga_klasa", nullable = false)
    private Integer sprzedaneDrugaKlasa;

    @ManyToOne
    @JoinColumn(name = "id_pociagu")
    private Pociag pociag;
    
    @OneToMany(mappedBy = "kurs")
    private Set<ZakupBiletu> zakupBiletu;
    

    public Kurs(LocalDate dataKursu, Integer miejscaPierwszaKlasa, Integer miejscaDrugaKlasa, Integer sprzedanePierwszaKlasa, Integer sprzedaneDrugaKlasa) {
        this.dataKursu = dataKursu;
        this.miejscaPierwszaKlasa = miejscaPierwszaKlasa;
        this.miejscaDrugaKlasa = miejscaDrugaKlasa;
        this.sprzedanePierwszaKlasa = sprzedanePierwszaKlasa;
        this.sprzedaneDrugaKlasa = sprzedaneDrugaKlasa;
        pociag = null;
        zakupBiletu = new HashSet<ZakupBiletu>();
    }

    protected Kurs() {
    }

    public Long getIdKursu() {
        return idKursu;
    }

    public void setIdKursu(Long idKursu) {
        this.idKursu = idKursu;
    }

    public LocalDate getDataKursu() {
        return dataKursu;
    }

    public void setDataKursu(LocalDate dataKursu) {
        this.dataKursu = dataKursu;
    }

    public Integer getMiejscaPierwszaKlasa() {
        return miejscaPierwszaKlasa;
    }

    public void setMiejscaPierwszaKlasa(Integer miejscaPierwszaKlasa) {
        this.miejscaPierwszaKlasa = miejscaPierwszaKlasa;
    }

    public Integer getMiejscaDrugaKlasa() {
        return miejscaDrugaKlasa;
    }

    public void setMiejscaDrugaKlasa(Integer miejscaDrugaKlasa) {
        this.miejscaDrugaKlasa = miejscaDrugaKlasa;
    }

    public Integer getSprzedanePierwszaKlasa() {
        return sprzedanePierwszaKlasa;
    }

    public void setSprzedanePierwszaKlasa(Integer sprzedanePierwszaKlasa) {
        this.sprzedanePierwszaKlasa = sprzedanePierwszaKlasa;
    }

    public Integer getSprzedaneDrugaKlasa() {
        return sprzedaneDrugaKlasa;
    }

    public void setSprzedaneDrugaKlasa(Integer sprzedaneDrugaKlasa) {
        this.sprzedaneDrugaKlasa = sprzedaneDrugaKlasa;
    }

    public Pociag getPociag() {
        return pociag;
    }

    public void setPociag(Pociag pociag) {
        this.pociag = pociag;
    }

    @Override
    public String toString() {
        String data = dataKursu.toString();
        return String.format("[%s - %s - %s - %s - %s - %s]", idKursu, data, miejscaPierwszaKlasa, miejscaDrugaKlasa, sprzedanePierwszaKlasa, sprzedaneDrugaKlasa);
    }
}