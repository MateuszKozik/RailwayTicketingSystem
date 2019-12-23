package com.kozik.RailwayTicketingSystem.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "uzytkownik")
public class Uzytkownik {
    
    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @Column(name = "haslo", nullable = false)
    private String haslo;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled = true;
    
    @OneToOne(mappedBy = "uzytkownik")
    private Maszynista maszynista;
    
    @OneToOne(mappedBy = "uzytkownik")
    private Pasazer pasazer;

    @ManyToMany
    @JoinTable(name = "uzytkownik_uprawnienia",joinColumns = {
        @JoinColumn(name = "uzytkownik_email", referencedColumnName = "email")}, inverseJoinColumns = {
        @JoinColumn(name = "uprawnienia_nazwa", referencedColumnName = "nazwa")})
    private List<Uprawnienia> uprawnienia;
    
    public Uzytkownik() {
    }

    public Uzytkownik( String haslo, String email) {
        this.haslo = haslo;
        this.email = email;
        maszynista = null;
        enabled = true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Maszynista getMaszynista() {
        return maszynista;
    }

    public void setMaszynista(Maszynista maszynista) {
        this.maszynista = maszynista;
    }

    public Pasazer getPasazer() {
        return pasazer;
    }

    public void setPasazer(Pasazer pasazer) {
        this.pasazer = pasazer;
    }  

    public List<Uprawnienia> getUprawnienia() {
        return uprawnienia;
    }

    public void setUprawnienia(List<Uprawnienia> uprawnienia) {
        this.uprawnienia = uprawnienia;
    }
    
    
}

