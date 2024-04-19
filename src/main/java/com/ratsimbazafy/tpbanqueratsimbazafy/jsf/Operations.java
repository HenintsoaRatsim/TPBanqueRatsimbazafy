/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.ratsimbazafy.tpbanqueratsimbazafy.jsf;

import com.ratsimbazafy.tpbanqueratsimbazafy.entity.CompteBancaire;
import com.ratsimbazafy.tpbanqueratsimbazafy.entity.OperationBancaire;
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
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private Long id;
    private CompteBancaire compte;
    private List<OperationBancaire> allOperations;
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

    public List<OperationBancaire> getAllOperations() {
        return this.getCompte().getOperations();
    }

    public void setAllOperations(List<OperationBancaire> allOperations) {
        this.allOperations = allOperations;
    }

   
    
    /**
     * Creates a new instance of Operations
     */
    public Operations() {
    }
    
    public void loadCompte() {
        this.compte = gestionnaireCompte.findById(this.getId());
    }
}
