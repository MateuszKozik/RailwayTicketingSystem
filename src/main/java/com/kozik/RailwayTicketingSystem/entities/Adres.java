package com.kozik.RailwayTicketingSystem.entities;

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
@Table(name = "adres")
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adresu", nullable = false)
    private Long idAdresu;

    @Column(name = "ulica", nullable = false)
    private String ulica;

    @Column(name = "numer_domu", nullable = false)
    private Integer numerDomu;

    @Column(name = "numer_mieszkania", nullable = true)
    private Integer numerMieszkania;

    @Column(name = "kod_pocztowy", nullable = false)
    private String kodPocztowy;

    @Column(name = "miejscowosc", nullable = false)
    private String miejscowosc;
    
    @OneToMany(mappedBy = "adres")
    private Set<Kierowca> kierowca;

    public Adres() {}

    public Adres(String ulica, Integer numerDomu, Integer numerMieszkania, String kodPocztowy, String miejscowosc) {
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.numerMieszkania = numerMieszkania;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
        kierowca = new HashSet<Kierowca>();
    }

    public Adres(String ulica, Integer numerDomu, String kodPocztowy, String miejscowosc) {
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
        kierowca = new HashSet<Kierowca>();
    }

    public Long getIdAdresu() {
        return idAdresu;
    }

    public void setIdAdresu(Long idAdresu) {
        this.idAdresu = idAdresu;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNumerDomu() {
        if (numerDomu == null){
            return "";
        }else{
          return String.valueOf(numerDomu);   
        }   
    }

    public void setNumerDomu(String numerDomu) {
        if(numerDomu == "")
        {
            this.numerDomu = null ;      
        }else{
            this.numerDomu = Integer.parseInt(numerDomu);
        }
       
    }

    public String getNumerMieszkania() {
         if (numerMieszkania == null){
            return "";
        }else{
          return String.valueOf(numerMieszkania);   
        } 
    }

    public void setNumerMieszkania(String numerMieszkania) {
        if(numerMieszkania == ""){
           this.numerMieszkania = null; 
        }else{
            this.numerMieszkania = Integer.parseInt(numerMieszkania);
        }      
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public Set<Kierowca> getKierowca() {
        return kierowca;
    }

    public void setKierowca(Set<Kierowca> kierowca) {
        this.kierowca = kierowca;
    }
    
    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s]", idAdresu,ulica,numerDomu,numerMieszkania,kodPocztowy,miejscowosc);
    }
}

