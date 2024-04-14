/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ratsimbazafy.tpbanqueratsimbazafy.jsf;

import com.ratsimbazafy.tpbanqueratsimbazafy.entity.CompteBancaire;
import com.ratsimbazafy.tpbanqueratsimbazafy.jsf.util.Util;
import com.ratsimbazafy.tpbanqueratsimbazafy.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author asus
 */
@Named(value = "ajoutCompte")
@ViewScoped
public class AjoutCompte implements Serializable {

    private String nom;
    private int montant;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }

    public String ajouter() {
        try {
            CompteBancaire c = new CompteBancaire(this.nom, this.montant);
            gestionnaireCompte.creerCompte(c);
            Util.addFlashInfoMessage("l'ajout du client  " + c.getNom() + " est correctement effectué  ");
            return "listeComptes?faces-redirect=true";
        } catch (Exception e) {
            Util.VerifExecption(e);
            return null;
        }
    }
}
