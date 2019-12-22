package com.kozik.RailwayTicketingSystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "uzytkownik")
public class Uzytkownik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_uzytkownika", nullable = false)
    private Long idUzytkownika;

    @Column(name = "nazwa_uzytkownika", nullable = false, unique = true)
    private String nazwaUzytkownika;

    @Column(name = "haslo", nullable = false)
    private String haslo;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "typ_konta", nullable = false)
    private String typKonta = "USER";

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
    
    @OneToOne(mappedBy = "uzytkownik")
    private Maszynista maszynista;
    
    @OneToOne(mappedBy = "uzytkownik")
    private Pasazer pasazer;

    public Uzytkownik() {
    }

    public Uzytkownik(String nazwaUzytkownika, String haslo, String email) {
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.haslo = haslo;
        this.email = email;
        maszynista = null;
        enabled = true;
    }

    public Long getIdUzytkownika() {
        return idUzytkownika;
    }

    public void setIdUzytkownika(Long idUzytkownika) {
        this.idUzytkownika = idUzytkownika;
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        this.nazwaUzytkownika = nazwaUzytkownika;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTypKonta() {
        return typKonta;
    }

    public void setTypKonta(String typKonta) {
        this.typKonta = typKonta;
    }

    public Maszynista getMaszynista() {
        return maszynista;
    }

    public void setMaszynista(Maszynista maszynista) {
        this.maszynista = maszynista;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Pasazer getPasazer() {
        return pasazer;
    }

    public void setPasazer(Pasazer pasazer) {
        this.pasazer = pasazer;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s]", idUzytkownika, nazwaUzytkownika, haslo, typKonta);
    }
}

