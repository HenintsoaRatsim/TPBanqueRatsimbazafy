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
import jakarta.persistence.OptimisticLockException;
import java.io.Serializable;

/**
 *
 * @author asus
 */
@Named(value = "mouvement")
@ViewScoped
public class Mouvement implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private String typeMouvement;
    private int montant;
    @Inject
    private GestionnaireCompte gestionnaireCompte;

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

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of Mouvement
     */
    public Mouvement() {
    }

    public void loadCompte() {
        this.compte = gestionnaireCompte.findById(this.getId());
    }

    public String enregistrerMouvement() {
        try {
            if (this.typeMouvement.equalsIgnoreCase("ajout")) {
                this.getCompte().deposer(montant);
                this.gestionnaireCompte.update(this.getCompte());
                Util.addFlashInfoMessage("L'ajout  de " + montant + " est correctement effectué pour " + this.getCompte().getNom());
            }
            if (this.typeMouvement.equalsIgnoreCase("retrait")) {
                this.getCompte().retirer(montant);
                this.gestionnaireCompte.update(this.getCompte());
                Util.addFlashInfoMessage("Le retrait  de " + montant + " est correctement effectué pour " + this.getCompte().getNom());
            }
            return "listeComptes?faces-redirect=true";
        } catch (Exception e) {
            Util.VerifExecption(e);
            if (e instanceof OptimisticLockException) {
                Util.messageErreur("Le compte de " + compte.getNom()
                        + " a été modifié ou supprimé par un autre utilisateur !");
            }
            return null;
        }
    }
}
