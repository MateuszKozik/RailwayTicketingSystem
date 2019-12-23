package com.kozik.RailwayTicketingSystem.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uprawnienia")
public class Uprawnienia {
    
    @Id
    @Column(name = "nazwa", nullable = false)
    private String nazwa;

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
}
