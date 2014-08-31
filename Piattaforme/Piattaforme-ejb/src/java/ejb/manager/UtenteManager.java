/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Cliente;
import facade.ClienteFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import manager.StatoClienti;

/**
 *
 * @author maidenfp
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UtenteManager implements UtenteManagerLocal {
    
    @EJB
    private ClienteFacadeLocal clienteFacade;

    @Override
    public void attivaCliente(Cliente c) {

        if (c == null) {
            return;
        }
        if (c.getStato().equals(StatoClienti.Attivo) ) {
            return;
        }

        c.setStato(StatoClienti.Attivo);
    }
    
    @Override
    public void bloccaCliente(Cliente c) {

        if (c == null) {
            return;
        }
        if (c.getStato().equals(StatoClienti.Bloccato) ) {
            return;
        }

        c.setStato(StatoClienti.Bloccato);
    }
    
    @Override
    public Cliente cercaClientePerId(Long id) {

        if (id == null) {
            return null;
        }
        return clienteFacade.find(id);
    }
    
    
        
    
 
}
