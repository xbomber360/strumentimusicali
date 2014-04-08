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
public interface UtenteManagerLocal {
    
    void attivaCliente(Cliente c);

    void bloccaCliente(Cliente c);
    
    Cliente cercaClientePerId(Long id);
    
}
