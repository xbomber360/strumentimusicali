/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.login;

import ejb.amministratore.AmministratoreLocal;
import ejb.cliente.ClienteLocal;
import ejb.gestoremagazzino.GestoreMagazzinoLocal;
import ejb.manager.ClienteManager;
import entity.Cliente;
import entity.Utente;
import javax.ejb.EJB;
import javax.ejb.Stateful;
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
    private GestoreMagazzinoLocal gestoremagEJB;
    @EJB
    private AmministratoreLocal amministratoreEJB;
    @EJB
    private ClienteLocal clienteEJB;
    @EJB
    private ClienteManager manager;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Utente accesso(String username, String password) {
        Cliente cliente = new Cliente();
        cliente.setUsername(username);
        
        // verifico se esiste l'account
        
        if (!manager.ePresente(cliente)) {
            throw new ClienteNonPresenteException();
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}