/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ratsimbazafy.tpbanqueratsimbazafy.service;

import com.ratsimbazafy.tpbanqueratsimbazafy.entity.CompteBancaire;
import com.ratsimbazafy.tpbanqueratsimbazafy.jsf.util.Util;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;

/**
 * Façade pour gérer les Customers.
 *
 * @author asus
 */
@DataSourceDefinition(
        className = "com.mysql.cj.jdbc.MysqlDataSource",
        name = "java:app/jdbc/banque",
        serverName = "localhost",
        portNumber = 3306,
        user = "root",
        password = "root",
        databaseName = "banque",
        properties = {
            "useSSL=false",
            "allowPublicKeyRetrieval=true",
            "driverClass=com.mysql.cj.jdbc.Driver"
        }
)

@ApplicationScoped
public class GestionnaireCompte {

    @PersistenceContext(unitName = "banquePU")
    private EntityManager em;

    @Transactional
    public void creerCompte(CompteBancaire c) {
        em.persist(c);
    }

    public List<CompteBancaire> getAllComptes() {
        Query query = em.createNamedQuery("comptebancaire.findAll");
        return query.getResultList();
    }

    public long nbComptes() {
        Query query = em.createQuery("SELECT COUNT(c) FROM CompteBancaire c");
        return (long) query.getSingleResult();
    }

    public CompteBancaire findById(Long id) {
        return em.find(CompteBancaire.class, id);
    }

    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) {
        return em.merge(compteBancaire);
    }

    @Transactional
    public boolean transferer(Long idSource, Long idDestinataire, int montant) {
        try {
            boolean erreur = false;
            CompteBancaire compteSource = this.findById(idSource);
            CompteBancaire compteDestinataire = this.findById(idDestinataire);
            if (compteSource == null || compteDestinataire == null) {
                Util.messageErreur("Aucun compte avec l'id " + idSource, "Aucun compte avec l'id " + idSource, "form:source");
                erreur = true;
            }
            if (compteDestinataire == null) {
                Util.messageErreur("Aucun compte avec l'id " + idDestinataire, "Aucun compte avec l'id " + idDestinataire, "form:destinataire");
                erreur = true;
            } else {
                if (compteSource.getSolde() < montant) {
                    Util.messageErreur("solde compte source insuffisant ", "solde compte source insuffisan ", "form:montant");
                    erreur = true;
                }
            }

            if (erreur) {
                return false;
            }
            compteSource.retirer(montant);
            compteDestinataire.deposer(montant);
            this.update(compteSource);
            this.update(compteDestinataire);
            Util.addFlashInfoMessage("Transfert de "+montant+"  correctement effectué entre "+compteSource.getNom()+" et "+compteDestinataire.getNom());
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
