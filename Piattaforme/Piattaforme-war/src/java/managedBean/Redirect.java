/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import entity.Utente;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author siciliano
 */
public class Redirect implements Serializable {

    public static boolean accesso;
    public static boolean accessoCliente;
    public static boolean accessoAmministratore;
    public static boolean accessoGestoreMagazzino;
    public Utente utente;
    
    public Redirect() {
    }

    public boolean isAccesso() {
        return accesso;
    }

    public void setAccesso(boolean accesso) {
        this.accesso = accesso;
    }

    public boolean isAccessoCliente() {
        return accessoCliente;
    }

    public void setAccessoCliente(boolean accessoCliente) {
        this.accessoCliente = accessoCliente;
    }

    public boolean isAccessoAmministratore() {
        return accessoAmministratore;
    }

    public  void setAccessoAmministratore(boolean accessoAmministratore) {
        this.accessoAmministratore = accessoAmministratore;
    }

    public boolean isAccessoGestoreMagazzino() {
        return accessoGestoreMagazzino;
    }

    public void setAccessoGestoreMagazzino(boolean accessoGestoreMagazzino) {
        this.accessoGestoreMagazzino = accessoGestoreMagazzino;
    }
    
    

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public void redirect(String pageJSF) {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {

            if (pageJSF == null) {

                ec.redirect("");
            } else {

                ec.redirect(pageJSF);
            }
        } catch (IOException e) {
        }

    }
    
    /**
     * Il metodo effettua il redirect: se flag = true il redirect avviene se il
     * login e' stato effettuato; se flag = false il redirect avviene se il
     * login non e' stato effettuato.
     *
     * @param pageJSF Pagina JSF su cui effettuare il redirect, se e' null il
     * redirect sara' fatto sull' index.jsf.
     *
     * @param flag true per login effettuato, false per login non effettuato.
     */
    public void redirectLogin(String pageJSF, boolean flag) {

        if (!this.accesso && !flag || this.accesso && flag) {

            this.redirect(pageJSF);
        }
    }
    
    
    /**
     * Consente di effettuare un redirect in caso in cui si cerca di accedere
     * con un account diverso dal cliente.
     *
     * @param gestioneCliente se l'id e' null attiva il redirect.
     *
     * @param pageJSF Pagina su cui effettuare il redirect, se null il redirect
     * avviene sull'index.
     */
    public void redirectCliente(GestioneCliente gestioneCliente, String pageJSF) {


        if (gestioneCliente.getId() == null) {

            this.redirect(pageJSF);
        }
    }// redirectCliente

    /**
     * Consente di effettuare un REDIRECT in caso in cui si cerca di accedere
     * con un account diverso dal Moderatore.
     *
     * @param pageJSF Pagina su cui effettuare il redirect, se null il redirect
     * avviene sull'index.
     * 
     */
    public void redirectGestoreMagazzino (GestioneSito gestoreMagazzino, String pageJSF) {


        if (gestoreMagazzino.getIDGestoreMagazzino()== null) {

            this.redirect(pageJSF);
        }
    }// redirectModeratore

    /**
     * Il metodo regolamenta l'accesso alla pagina gestoreSito. IL REDIRECT si
     * attiva quando sia il supervisore che il moderatore hanno id = null.
     *
     * @param gestioneSito Parametro contenente l'id del supervisore, del
     * moderatore e dell'amministratore.
     * @param pageJSF Pagina jsf su cui attivare il redirect, se null, il
     * redirect avviene sull'index.
     */
    public void redirectGestoreSito(GestioneSito gestioneSito, String pageJSF) {

        if (gestioneSito.getIDGestoreMagazzino() == null && gestioneSito.getIDAmministratore() == null) {

            this.redirect(pageJSF);
        }
    }// redirectGestoreSito

    

    public void logout() {

        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        
        

        try {

            ec.redirect("/Piattaforme-war/Login.jsf");
        } catch (IOException ex) {

            Logger.getLogger(Redirect.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }// logout


    @PostConstruct
    public void init() {

        this.accesso = false;
        this.accessoCliente = false;
        this.accessoAmministratore = false;
        this.accessoGestoreMagazzino = false;
        this.utente = null;
    }
}

    

