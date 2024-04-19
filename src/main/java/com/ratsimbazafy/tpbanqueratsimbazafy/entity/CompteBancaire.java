/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ratsimbazafy.tpbanqueratsimbazafy.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author asus
 */
@Entity
@Table(name = "comptebancaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "comptebancaire.findAll", query = "select cb from CompteBancaire cb")
})
public class CompteBancaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private int solde;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OperationBancaire> operations = new ArrayList<>();

    public List<OperationBancaire> getOperations() {
        return operations;
    }
    
    public CompteBancaire() {

    }

    public CompteBancaire(String nom, int solde) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new RuntimeException("Le nom du compte ne peut pas être vide ou null.");
        }
        if (solde < 0) {
            throw new RuntimeException("Le solde du compte ne peut pas être négatif.");
        }
        operations.add(new OperationBancaire("Création du compte", solde));
        this.nom = nom;
        this.solde = solde;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String Nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new RuntimeException("Le nom du compte ne peut pas être vide ou null.");
        }
        this.nom = Nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int Solde) {
        if (solde < 0) {
            throw new RuntimeException("Le solde du compte ne peut pas être négatif.");
        }
        this.solde = Solde;
    }

    public void deposer(int montant) {
        if (montant < 0) {
            throw new RuntimeException("Le solde du compte ne peut pas être négatif.");
        }
        operations.add(new OperationBancaire("Créditer  compte", solde));
        solde += montant;
    }

    public void retirer(int montant) {
        if (montant < 0) {
            throw new RuntimeException("Le solde du compte ne peut pas être négatif.");
        }
        if (montant > solde) {
            throw new RuntimeException("le montant est suppérieur au solde.");
        } 
                operations.add(new OperationBancaire("Débiter  compte", -solde));

        solde -= montant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompteBancaire)) {
            return false;
        }
        CompteBancaire other = (CompteBancaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ratsimbazafy.tpbanqueratsimbazafy.entity.CompteBancaire[ id=" + id + " ]";
    }

    
}
