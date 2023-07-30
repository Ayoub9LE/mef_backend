package com.mef.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "concours")
public class Concours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false, name = "nombre_de_participants")
    private Integer nombreDeParticipants;

    @Column(nullable = false, name = "nombre_de_places")
    private Integer nombreDePlaces;

    @Column(nullable = false, name = "frais_d_inscription")
    private BigDecimal fraisDInscription;

    @Column(nullable = false)
    private String règles;

    @Column(nullable = false)
    private String lieu;

    @Column(nullable = false, name = "date_de_debut")
    private LocalDate dateDeDebut;

    @Column(nullable = false, name = "date_de_fin")
    private LocalDate dateDeFin;

    @Column(nullable = false)
    private String etat;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNombreDeParticipants() {
        return nombreDeParticipants;
    }

    public void setNombreDeParticipants(Integer nombreDeParticipants) {
        this.nombreDeParticipants = nombreDeParticipants;
    }

    public Integer getNombreDePlaces() {
        return nombreDePlaces;
    }

    public void setNombreDePlaces(Integer nombreDePlaces) {
        this.nombreDePlaces = nombreDePlaces;
    }

    public BigDecimal getFraisDInscription() {
        return fraisDInscription;
    }

    public void setFraisDInscription(BigDecimal fraisDInscription) {
        this.fraisDInscription = fraisDInscription;
    }

    public String getRègles() {
        return règles;
    }

    public void setRègles(String règles) {
        this.règles = règles;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDateDeDebut() {
        return dateDeDebut;
    }

    public void setDateDeDebut(LocalDate dateDeDebut) {
        this.dateDeDebut = dateDeDebut;
    }

    public LocalDate getDateDeFin() {
        return dateDeFin;
    }

    public void setDateDeFin(LocalDate dateDeFin) {
        this.dateDeFin = dateDeFin;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}

