package com.kozik.PKPTicket.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "znizka")
public class Znizka {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_znizki", nullable = false)
    private Long idZnizki;
    
    @Column(name = "opis", nullable = false)
    private String opis;
    
    @Column(name = "procent_znizki", nullable = false)
    private Integer procentZnizki;
    
    @OneToMany(mappedBy = "znizka")
    private Set<Bilet> bilet;
    
    
    public Znizka(){}

    public Znizka(String opis, Integer procentZnizki) {
        this.opis = opis;
        this.procentZnizki = procentZnizki;
        bilet = new HashSet<Bilet>();
    }

    public Long getIdZnizki() {
        return idZnizki;
    }

    public void setIdZnizki(Long idZnizki) {
        this.idZnizki = idZnizki;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Integer getProcentZnizki() {
        return procentZnizki;
    }

    public void setProcentZnizki(Integer procentZnizki) {
        this.procentZnizki = procentZnizki;
    }

    public Set<Bilet> getBilet() {
        return bilet;
    }

    public void setBilet(Set<Bilet> bilet) {
        this.bilet = bilet;
    }
    
    @Override
    public String toString() {
        return String.format("[%s - %s - %s]", idZnizki,opis,procentZnizki);
    }
    
}