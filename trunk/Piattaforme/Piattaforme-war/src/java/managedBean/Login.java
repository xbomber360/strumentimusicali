/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.cliente.ClienteLocal;
import ejb.login.LoginLocal;
import ejb.manager.ClienteManagerLocal;
import entity.Amministratore;
import entity.GestoreMagazzino;
import entity.Utente;
import exception.AccountException;
import exception.ClienteLoginException;
import exception.ClienteNonPresenteException;
import exception.UtenteBloccatoException;
import javax.ejb.EJB;

/**
 *
 * @author maidenfp
 */
public class Login {

    /**
     * Creates a new instance of Login
     */
    
    @EJB
    private LoginLocal login;
    @EJB
    private ClienteManagerLocal manager;
    private ClienteLocal c;
    private String username;
    private String password;
    private String nuovaPassword;
    private Utente utente;
    private Redirect redirect ;
   
    
    public Login() { 
      
    }
    
    public Utente getUtente() {
        return utente;
    }
    
    public void setUtente(Utente u) {
        utente = u;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getNuovaPassword() {
        return nuovaPassword;
    }

    public void setNuovaPassword(String nuovaPassword) {
        this.nuovaPassword = nuovaPassword;
    }
    
    
    
    public String accesso (GestioneCliente gc, GestioneSito gs)  {
                    
 
        try {

            utente = login.accesso(username, password);
            System.out.println(utente);
            System.out.println("lavoro con un istanza di tipo" + utente.getClass());
            redirect = new Redirect();
            redirect.setAccesso(true);
            redirect.setUtente(utente);
            
            
            if (utente instanceof Amministratore) {
                System.out.println("Sono un amministratore");
                gs.setIDAmministratore(utente.getId());
                gs.setNomeAmministratore(utente.getNome());
                redirect.setAccessoAmministratore(true);
              
                return "AccessoAmministratore";
            }
            else if (utente instanceof GestoreMagazzino) {
                System.out.println("Sono un gestore magazzino");
                gs.setIDGestoreMagazzino(utente.getId());
                gs.setNomeGestoreMagazzino(utente.getNome());
                redirect.setAccessoGestoreMagazzino(true);
                return "AccessoMagazzino";
            }
            System.out.println("Sono un cliente");
            gc.setNome(utente.getNome());
            gc.setId(utente.getId());
            redirect.setAccessoCliente(true);
            
            return "AccessoCliente";
            
        }catch (ClienteLoginException ex ) {
            System.out.println("clienteloginexception");
            return "Errore client login";
        } catch (UtenteBloccatoException ex) {
                        System.out.println("utentebloccatoexc");

            return "UtenteBloccato";
        } catch (ClienteNonPresenteException ex) {
                                    System.out.println("nonpreenteexce");

            return "cliente non presente";
        }
        
        
    }
    
    public String reimposta() {
        System.out.println(username);
        try {
            manager.modificaPassword(nuovaPassword, username);
            return "PM";
        } catch (AccountException ex) {
            
            return "PNM";
        }

    }
}
