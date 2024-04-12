/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ratsimbazafy.tpbanqueratsimbazafy.jsf;

import com.ratsimbazafy.tpbanqueratsimbazafy.entity.CompteBancaire;
import com.ratsimbazafy.tpbanqueratsimbazafy.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author asus
 */
@Named(value = "modification")
@ViewScoped
public class Modification implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String nom;
    private int Solde;
    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return Solde;
    }

    public void setSolde(int Solde) {
        this.Solde = Solde;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CompteBancaire getCompte() {
        return compte;
    }

    public void setCompte(CompteBancaire compte) {
        this.compte = compte;
    }

    /**
     * Creates a new instance of Modification
     */
    public Modification() {
    }

    public void loadCompte() {
        this.compte = gestionnaireCompte.findById(this.getId());
        this.nom = this.compte.getNom();
        this.Solde = this.compte.getSolde();
    }

    public String enregistrerModification() {
        boolean rep = gestionnaireCompte.Modification(this.compte,this.nom,this.Solde);
        if(rep)return null;
        return "listeComptes?faces-redirect=true";
    }
}
