/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.login.LoginLocal;
import entity.Amministratore;
import entity.GestoreMagazzino;
import entity.Utente;
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
    private String username;
    private String password;
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
    
    public String accesso ()  {
                    
 
        try {

            utente = login.accesso(username, password);
            redirect = new Redirect();
            redirect.setAccesso(true);
            redirect.setUtente(utente);
            
            if (utente instanceof Amministratore) {
                
                //sito.setIDAmministratore(utente.getId());
                //sito.setNomeAmministratore(utente.getNome());
              
                return "AccessoAmministratore";
            }
            else if (utente instanceof GestoreMagazzino) {
                //sito.setIDGestoreMagazzino(utente.getId());
                //sito.setNomeGestoreMagazzino(utente.getNome());
                return "AccessoMagazzino";
            }
            
            //cliente.setNome(utente.getNome());
            //cliente.setId(utente.getId());
            return "AccessoCliente";
            
        }catch (ClienteLoginException ex ) {
            System.out.println("clienteloginexception");
            return "Errore client login";
        } catch (UtenteBloccatoException ex) {
                        System.out.println("utentebloccatoexc");

            return "utente bloccato";
        } catch (ClienteNonPresenteException ex) {
                                    System.out.println("nonpreenteexce");

            return "cliente non presente";
        }
        
        
    }
}
