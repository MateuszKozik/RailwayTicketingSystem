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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "maszynista")
public class Maszynista {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_maszynisty", nullable = false)
    private Long idMaszynisty;

    @Column(name = "imie", nullable = false)
    private String imie;

    @Column(name = "nazwisko", nullable = false)
    private String nazwisko;

    @Column(name = "pesel", nullable = false)
    private String pesel;

    @OneToOne
    @JoinColumn(name = "id_uzytkownika", unique = true, nullable = true)
    private Uzytkownik uzytkownik;

    @ManyToOne
    @JoinColumn(name = "id_adresu")
    private Adres adres;
    
    @OneToMany(mappedBy = "maszynista")
    private Set<Pociag> pociag;
    

    public Maszynista() {
    }

    public Maszynista(String imie, String nazwisko, String pesel) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        adres = null;
        pociag = new HashSet<Pociag>();
    }

    public Long getIdMaszynisty() {
        return idMaszynisty;
    }

    public void setIdMaszynisty(Long idMaszynisty) {
        this.idMaszynisty = idMaszynisty;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public Set<Pociag> getPociag() {
        return pociag;
    }

    public void setPociag(Set<Pociag> pociag) {
        this.pociag = pociag;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s]", idMaszynisty, imie, nazwisko, pesel);
    }
}
