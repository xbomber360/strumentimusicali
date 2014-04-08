/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.login;

import entity.Utente;
import exception.ClienteLoginException;
import exception.ClienteNonPresenteException;
import exception.UtenteBloccatoException;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface LoginLocal {
    
    Utente accesso (String username, String password) throws UtenteBloccatoException, ClienteNonPresenteException, ClienteLoginException;
}
