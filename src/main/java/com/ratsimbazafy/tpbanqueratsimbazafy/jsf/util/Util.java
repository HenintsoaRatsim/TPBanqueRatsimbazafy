/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ratsimbazafy.tpbanqueratsimbazafy.jsf.util;

/**
 *
 * @author asus
 */
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;

/**
 * Classe utilitaire pour JSF.
 * Pour les messages, avec ou sans redirection.
 */
public class Util {

  /**
   * Avec clientId. 
   * Pour retrouver un clientId, sur un exemple : si le <h:inputText> 
   * a un id égal à "input" et s'il est dans un formulaire dont le
   * id est "form", clientId est "form:input".
   * On peut aussi le trouver en examinant le source de la page HTML.
   * @param messageDetail message détaillé.
   * @param messageResume message résumé.
   * @param severite sévérité du message.
   * @param clientId id du client qui est attaché au message.
   */
  public static void message(String messageDetail, String messageResume,
          FacesMessage.Severity severite, String clientId) {
    FacesMessage msg =
            new FacesMessage(severite, messageResume, messageDetail);
    FacesContext.getCurrentInstance().addMessage(clientId, msg);
  }

  public static void VerifExecption(Exception e){
      if (e.getMessage().contains("nom du compte ne peut pas être vide")) {
                Util.messageErreur(e.getMessage(), "nom vide", "form:nom");
            }if (e.getMessage().contains("solde du compte ne peut pas être négatif")) {
                Util.messageErreur(e.getMessage(), "solde négatif", "form:montant");
            }if (e.getMessage().contains("le montant est suppérieur au solde.")) {
                Util.messageErreur(e.getMessage(), "erreur montant", "form:montant");
            }if (e.getMessage().contains("Aucun compte avec cette id.")) {
                 Util.messageErreur(e.getMessage(), "Aucun compte avec cette id. ", "form:source");
            }
             
  }
  /**
   * Message qui n'est pas attaché à un composant particulier.
   * @param messageDetail
   * @param messageResume
   * @param severite 
   */
  public static void message(String messageDetail, String messageResume,
          FacesMessage.Severity severite) {
    FacesMessage msg =
            new FacesMessage(severite, messageResume, messageDetail);
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  /**
   * Message de sévérité FacesMessage.SEVERITY_ERROR qui n'est pas attaché
   * à un composant particulier. On peut donner un message différente pour
   * le message détaillé et pour le message résumé.
   * @param messageDetail
   * @param messageResume 
   */
  public static void messageErreur(String messageDetail, String messageResume) {
    message(messageDetail, messageResume, FacesMessage.SEVERITY_ERROR);
  }

  public static void messageErreur(String message) {
    message(message, message, FacesMessage.SEVERITY_ERROR);
  }
  
  /**
   * Message d'erreur attaché à un composant particulier.
   * @param messageDetail
   * @param messageResume
   * @param clientId 
   */
  public static void messageErreur(String messageDetail, String messageResume, String clientId) {
    message(messageDetail, messageResume, FacesMessage.SEVERITY_ERROR, clientId);
  }

  /**
   * Message d'information (ou de succès) qui n'est pas attaché à un composant
   * particulier.
   * @param messageDetail
   * @param messageResume 
   */
  public static void messageInfo(String messageDetail, String messageResume) {
    message(messageDetail, messageResume, FacesMessage.SEVERITY_INFO);
  }

  public static void messageInfo(String message) {
    message(message, message, FacesMessage.SEVERITY_INFO);
  }

  /**
   * Messages avec redirection, pas attaché à un composant particulier.
   * @param message à afficher
   */
  public static void addFlashMessage(FacesMessage message) {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    Flash flash = facesContext.getExternalContext().getFlash();
    flash.setKeepMessages(true);
    facesContext.addMessage(null, message);
  }
  
  /**
   * Message d'erreur avec redirection, pas attaché à un composant particulier.
   * @param message 
   */
  public static void addFlashErrorMessage(String message) {
    FacesMessage msg = 
            new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
    addFlashMessage(msg);
  }
  
  /**
   * Message d'information avec redirection, pas attaché à un composant particulier.
   * @param message 
   */
  public static void addFlashInfoMessage(String message) {
    FacesMessage msg = 
            new FacesMessage(FacesMessage.SEVERITY_INFO, message, message);
    addFlashMessage(msg);
  }
}
 