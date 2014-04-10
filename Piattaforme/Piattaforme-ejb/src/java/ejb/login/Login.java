/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.login;

import ejb.manager.ClienteManagerLocal;
import entity.Cliente;
import entity.Utente;
import exception.ClienteLoginException;
import exception.ClienteNonPresenteException;
import exception.UtenteBloccatoException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 *
 * @author maidenfp
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class Login implements LoginLocal {
    
    @EJB
    private ClienteManagerLocal manager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Utente accesso(String username, String password) throws UtenteBloccatoException, ClienteNonPresenteException, ClienteLoginException {
        Cliente cliente = new Cliente();
        cliente.setUsername(username);
        
        // verifico se esiste l'account
        
        if (!manager.ePresente(cliente)) {
            throw new ClienteLoginException();
        }
        
        cliente = manager.ottieniCliente(cliente);
        
        // controllo password
        
        try {
            
            if (!password.equals(cliente.getPassword())) {
                throw new ClienteLoginException() ;
            }
        } catch (Exception ex) {
            
        }
        return verificaCliente(cliente);
    }
    
    private Cliente verificaCliente(Cliente c) throws ClienteLoginException, UtenteBloccatoException {

        if (c == null) {

            throw new ClienteLoginException();
        }

        switch (c.getStato()) {

            case Bloccato:
                throw new UtenteBloccatoException();
            case Attivo:
                return manager.cercaPerEmail(c.getEmail());

            default:
                return null;
        }
    }
}

  