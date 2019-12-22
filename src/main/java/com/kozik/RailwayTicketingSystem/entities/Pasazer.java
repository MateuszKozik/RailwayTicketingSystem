package com.kozik.RailwayTicketingSystem.entities;


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
@Table(name = "pasazer")
public class Pasazer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_pasazera", nullable = false)
    private Long idPasazera;

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

    @OneToMany(mappedBy = "pasazer")
    private Set<ZakupBiletu> zakupBiletu;

    public Pasazer() {
    }

    public Pasazer(String imie, String nazwisko, String pesel, Uzytkownik uzytkownik) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.uzytkownik = uzytkownik;
        adres = null;
        zakupBiletu = new HashSet<ZakupBiletu>();
    }

    public Long getIdPasazera() {
        return idPasazera;
    }

    public void setIdPasazera(Long idPasazera) {
        this.idPasazera = idPasazera;
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

    public Set<ZakupBiletu> getZakupBiletu() {
        return zakupBiletu;
    }

    public void setZakupBiletu(Set<ZakupBiletu> zakupBiletu) {
        this.zakupBiletu = zakupBiletu;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s]", idPasazera, imie, nazwisko, pesel);
    }

}