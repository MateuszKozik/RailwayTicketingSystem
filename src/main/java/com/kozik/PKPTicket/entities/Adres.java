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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "adres")
public class Adres {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_adresu", nullable = false)
    private Long idAdresu;

    @Column(name = "ulica", nullable = false)
    @NotEmpty
    private String ulica;

    @Column(name = "numer_domu", nullable = false)
    @NotNull
    @Min(1)
    private Integer numerDomu;

    @Column(name = "numer_mieszkania", nullable = true)
    @Min(1)
    private Integer numerMieszkania;

    @Column(name = "kod_pocztowy", nullable = false)
    @NotEmpty
    @Pattern(regexp = "\\d{2}-\\d{3}")
    private String kodPocztowy;

    @Column(name = "miejscowosc", nullable = false)
    @NotEmpty
    private String miejscowosc;
    
    @OneToMany(mappedBy = "adres")
    private Set<Maszynista> maszynista;
    
    @OneToMany(mappedBy = "adres")
    private Set<Pasazer> pasazer;

    public Adres() {}

    public Adres(String ulica, Integer numerDomu, Integer numerMieszkania, String kodPocztowy, String miejscowosc) {
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.numerMieszkania = numerMieszkania;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
        this.maszynista = new HashSet<Maszynista>();
        this.pasazer = new HashSet<Pasazer>();
    }

    public Adres(String ulica, Integer numerDomu, String kodPocztowy, String miejscowosc) {
        this.ulica = ulica;
        this.numerDomu = numerDomu;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
        this.maszynista = new HashSet<Maszynista>();
        this.pasazer = new HashSet<Pasazer>();
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

    public Set<Maszynista> getMaszynista() {
        return maszynista;
    }

    public void setMaszynista(Set<Maszynista> maszynista) {
        this.maszynista = maszynista;
    }

    public Set<Pasazer> getPasazer() {
        return pasazer;
    }

    public void setPasazer(Set<Pasazer> pasazer) {
        this.pasazer = pasazer;
    }

    @Override
    public String toString() {
        return String.format("[%s - %s - %s - %s]", idAdresu,ulica,numerDomu,numerMieszkania,kodPocztowy,miejscowosc);
    }
}

