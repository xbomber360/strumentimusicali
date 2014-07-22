/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.login.LoginLocal;
import entity.Utente;
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
    
    
}
