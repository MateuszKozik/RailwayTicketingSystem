package com.kozik.PKPTicket.entities;

import java.time.LocalDateTime;
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
    private LocalDateTime dataKursu;

    @Column(name = "miejsca_pierwsza_klasa", nullable = false)
    private Integer miejscaPierwszaKlasa;

    @Column(name = "miejsca_druga_klasa", nullable = false)
    private Integer miejscaDrugaKlasa;

    @Column(name = "sprzedane_pierwsza_klasa", nullable = true)
    private Integer sprzedanePierwszaKlasa;

    @Column(name = "sprzedane_druga_klasa", nullable = true)
    private Integer sprzedaneDrugaKlasa;

    @ManyToOne
    @JoinColumn(name = "id_pociagu")
    private Pociag pociag;
    
    @OneToMany(mappedBy = "kurs")
    private Set<ZakupBiletu> zakupBiletu;
    

    public Kurs(LocalDateTime dataKursu, Integer miejscaPierwszaKlasa, Integer miejscaDrugaKlasa, Integer sprzedanePierwszaKlasa, Integer sprzedaneDrugaKlasa) {
        this.dataKursu = dataKursu;
        this.miejscaPierwszaKlasa = miejscaPierwszaKlasa;
        this.miejscaDrugaKlasa = miejscaDrugaKlasa;
        this.sprzedanePierwszaKlasa = sprzedanePierwszaKlasa;
        this.sprzedaneDrugaKlasa = sprzedaneDrugaKlasa;
        pociag = null;
        zakupBiletu = new HashSet<ZakupBiletu>();
    }

    public Kurs() {
    }

    public Long getIdKursu() {
        return idKursu;
    }

    public void setIdKursu(Long idKursu) {
        this.idKursu = idKursu;
    }

    public LocalDateTime getDataKursu() {
        return dataKursu;
    }

    public void setDataKursu(LocalDateTime dataKursu) {
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

    public Set<ZakupBiletu> getZakupBiletu() {
        return zakupBiletu;
    }

    public void setZakupBiletu(Set<ZakupBiletu> zakupBiletu) {
        this.zakupBiletu = zakupBiletu;
    }

    @Override
    public String toString() {
        String data = dataKursu.toString();
        return String.format("[%s - %s - %s - %s - %s - %s]", idKursu, data, miejscaPierwszaKlasa, miejscaDrugaKlasa, sprzedanePierwszaKlasa, sprzedaneDrugaKlasa);
    }
}