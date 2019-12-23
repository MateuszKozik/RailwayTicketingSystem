package com.kozik.RailwayTicketingSystem.entities;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "uprawnienia")
public class Uprawnienia {
    
    @Id
    @Column(name = "nazwa", nullable = false)
    private String nazwa;
   
    @ManyToMany(mappedBy = "uprawnienia")
    private List<Uzytkownik> uzytkownik;
    public Uprawnienia() {}

    public Uprawnienia(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public List<Uzytkownik> getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(List<Uzytkownik> uzytkownik) {
        this.uzytkownik = uzytkownik;
    }
}
