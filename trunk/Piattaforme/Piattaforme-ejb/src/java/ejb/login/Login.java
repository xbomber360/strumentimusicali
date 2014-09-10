/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.login;

import ejb.manager.ClienteManagerLocal;
import entity.Amministratore;
import entity.Cliente;
import entity.GestoreMagazzino;
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

        Utente utente = new Utente();
        try {
        utente = manager.ottieniUtente (username);
        } catch (Exception ce) {
            System.out.println("Cliente non presente");
        } 
        
        if ( utente == null) {
            System.out.println("[Login] Utente=null");
            return null;
        }
        // verifico se esiste l'account
        
        if (!manager.ePresente(utente)) {
            throw new ClienteLoginException();
        }
        
        if ( utente instanceof Cliente) {
            Cliente cliente = new Cliente();
        cliente = manager.ottieniCliente(utente);
            System.out.println("e un cliente");
             
        // controllo password
 
            if (!password.equals(utente.getPassword())) {
                throw new ClienteLoginException() ;
            }
        
        return verificaCliente(cliente);
         }
        else if ( utente instanceof Amministratore) {
            System.out.println("e un amministratore");
            Amministratore admin = new Amministratore();
            admin = manager.ottieniAmministratore(utente);
            if (!password.equals(utente.getPassword())) {
                throw new ClienteLoginException() ;
            }
            return admin;
            
        }
        else if ( utente instanceof GestoreMagazzino) {
            System.out.println("e un magazziniere");
            GestoreMagazzino gm = new GestoreMagazzino();
            gm = manager.ottieniGestoreMagazzino(utente);
            if (!password.equals(utente.getPassword())) {
                throw new ClienteLoginException() ;
            }
            return gm;
        }
      return niente();
    }
    
    private Utente niente(){
        System.out.println("nulla");
        return null;
    }
    
    
    
    private Cliente verificaCliente(Cliente c) throws ClienteLoginException, UtenteBloccatoException {
        System.out.println("verificaCliente");
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

  