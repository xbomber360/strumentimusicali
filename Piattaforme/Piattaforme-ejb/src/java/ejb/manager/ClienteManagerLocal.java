/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Cliente;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface ClienteManagerLocal {
    
    boolean ePresente (Cliente c);
    void creaCliente (Cliente c);
    void rimuoviCliente (Cliente c);
    Cliente ottieniCliente (Cliente c);
    Cliente cercaPerEmail (String email);
    Cliente cercaPerID (Long id);
   // void modificaPassword (String newPassword, Long Id);
    boolean esisteID (Long id);
    
}
