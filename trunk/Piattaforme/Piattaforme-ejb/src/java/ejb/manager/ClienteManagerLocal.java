/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Amministratore;
import entity.Cliente;
import entity.GestoreMagazzino;
import entity.Utente;
import exception.AccountException;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface ClienteManagerLocal {
    
    boolean ePresente (Utente u);
    void creaCliente (Cliente c);
    void rimuoviCliente (Cliente c);
    Utente ottieniUtente (String username);
    Cliente ottieniCliente (Utente u);
    Amministratore ottieniAmministratore (Utente u);
    GestoreMagazzino ottieniGestoreMagazzino (Utente u);
    Cliente cercaPerEmail (String email);
    Cliente cercaPerID (Long id);
    void modificaPassword (String newPassword, Long Id) throws AccountException;
    boolean esisteID (Long id);
    
}
