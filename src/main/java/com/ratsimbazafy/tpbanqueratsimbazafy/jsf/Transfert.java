/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ratsimbazafy.tpbanqueratsimbazafy.jsf;

import com.ratsimbazafy.tpbanqueratsimbazafy.service.GestionnaireCompte;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;

/**
 *
 * @author asus
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private Long idSource;
    private Long idDestinataire;
    private int montant;

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestinataire() {
        return idDestinataire;
    }

    public void setIdDestinataire(Long idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }

    public String transferer() {
        boolean rep=gestionnaireCompte.transferer(this.idSource, this.idDestinataire, this.montant);
        if(!rep)return null;
        return "listeComptes?faces-redirect=true";
    }
}
