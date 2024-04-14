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
        try {
            CompteBancaire c_source = this.gestionnaireCompte.findById(this.getIdSource());
            CompteBancaire c_destinatire = this.gestionnaireCompte.findById(this.getIdDestinataire());
            gestionnaireCompte.transferer(c_source, c_destinatire, this.getMontant());
            Util.addFlashInfoMessage("Transfert de " + montant + "  correctement effectué entre " + c_source.getNom() + " et " + c_destinatire.getNom());
            return "listeComptes?faces-redirect=true";
        } catch (Exception e) {
            Util.VerifExecption(e);
            return null;
        }
    }
}
