package com.kozik.PKPTicket.entities;

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
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "pociag")
public class Pociag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pociagu", nullable = false)
    private Long idPociagu;

    @Column(name = "nazwa", nullable = true)
    private String nazwa;

    @NotEmpty
    @Column(name = "stacja_poczatkowa", nullable = false)
    private String stacjaPoczatkowa;

    @NotEmpty
    @Column(name = "stacja_koncowa", nullable = false)
    private String stacjaKoncowa;

    @Column(name = "cena_pierwsza_klasa", nullable = false)
    private Double cenaPierwszaKlasa;

    @Column(name = "cena_druga_klasa", nullable = false)
    private Double cenaDrugaKlasa;

    @OneToMany(mappedBy = "pociag")
    private Set<Kurs> kurs;
    
    @ManyToOne
    @JoinColumn(name="id_maszynisty")
    private Maszynista maszynista;

    public Pociag() {}

    public Pociag(String stacjaPoczatkowa, String stacjaKoncowa, Double cenaPierwszaKlasa, Double cenaDrugaKlasa) {
        this.stacjaPoczatkowa = stacjaPoczatkowa;
        this.stacjaKoncowa = stacjaKoncowa;
        this.cenaPierwszaKlasa = cenaPierwszaKlasa;
        this.cenaDrugaKlasa = cenaDrugaKlasa;
        kurs = new HashSet<Kurs>();
        maszynista = null;
    }

    public Pociag(String nazwa, String stacjaPoczatkowa, String stacjaKoncowa, Double cenaPierwszaKlasa, Double cenaDrugaKlasa) {
        this.nazwa = nazwa;
        this.stacjaPoczatkowa = stacjaPoczatkowa;
        this.stacjaKoncowa = stacjaKoncowa;
        this.cenaPierwszaKlasa = cenaPierwszaKlasa;
        this.cenaDrugaKlasa = cenaDrugaKlasa;
        kurs = new HashSet<Kurs>();
    }

    public Long getIdPociagu() {
        return idPociagu;
    }

    public void setIdPociagu(Long idPociagu) {
        this.idPociagu = idPociagu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getStacjaPoczatkowa() {
        return stacjaPoczatkowa;
    }

    public void setStacjaPoczatkowa(String stacjaPoczatkowa) {
        this.stacjaPoczatkowa = stacjaPoczatkowa;
    }

    public String getStacjaKoncowa() {
        return stacjaKoncowa;
    }

    public void setStacjaKoncowa(String stacjaKoncowa) {
        this.stacjaKoncowa = stacjaKoncowa;
    }

    public Double getCenaPierwszaKlasa() {
        return cenaPierwszaKlasa;
    }

    public void setCenaPierwszaKlasa(Double cenaPierwszaKlasa) {
        this.cenaPierwszaKlasa = cenaPierwszaKlasa;
    }

    public Double getCenaDrugaKlasa() {
        return cenaDrugaKlasa;
    }

    public void setCenaDrugaKlasa(Double cenaDrugaKlasa) {
        this.cenaDrugaKlasa = cenaDrugaKlasa;
    }

    public Set<Kurs> getKurs() {
        return kurs;
    }

    public void setKurs(Set<Kurs> kurs) {
        this.kurs = kurs;
    }

    public Maszynista getMaszynista() {
        return maszynista;
    }

    public void setMaszynista(Maszynista maszynista) {
        this.maszynista = maszynista;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s - %s - %s]", idPociagu, nazwa, stacjaPoczatkowa, stacjaKoncowa, cenaPierwszaKlasa, cenaDrugaKlasa);
    }
}
