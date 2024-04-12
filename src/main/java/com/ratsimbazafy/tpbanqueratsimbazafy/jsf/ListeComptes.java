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
import java.util.List;

/**
 *
 * @author asus
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte;

    public ListeComptes() {
    }

    public List<CompteBancaire> getAllComptes() {
        return gestionnaireCompte.getAllComptes();
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        gestionnaireCompte.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
}
