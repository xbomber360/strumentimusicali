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
import facade.UtenteFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author maidenfp
 */
@Stateless
public class ClienteManager implements ClienteManagerLocal {

    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    @EJB
    private UtenteFacadeLocal utenteFacade;
    
    @Override
    public boolean ePresente(Utente u) {
        Query q = em.createNamedQuery("cercaUtentePerUsername");
        q.setParameter(1, u.getUsername());
        List<Utente> l = q.getResultList();
        if (!l.isEmpty()) {
            return true;
        }
        return false;
    }
    
    @Override
    public void creaCliente(Cliente c) {
        utenteFacade.create(c);
    }
    
    @Override
    public void creaGestoreMagazzino(GestoreMagazzino gm) {
        utenteFacade.create(gm);
    }

    @Override
    public void rimuoviCliente(Cliente c) {
        utenteFacade.remove(c);
    }
    
    @Override
    public Cliente ottieniCliente(Utente u) {
        
        Query q = em.createNamedQuery("Utente.isRegistrato");
        q.setParameter(1, u.getUsername());
        System.out.println("Dal database prelevo un istanza di tipo"+  q.getSingleResult());
        return (Cliente) q.getSingleResult();
    }
    
    @Override
    public Utente ottieniUtente(String  s) {
        
        Query q = em.createNamedQuery("cercaUtentePerUsername");
        q.setParameter(1, s);
        System.out.println("Dal database prelevo un istanza di tipo"+  q.getSingleResult());
        return (Utente) q.getSingleResult();
    }
    
    @Override
    public Utente ottieniUtenteEmail(String  s) {
        
        Query q = em.createNamedQuery("cercaUtentePerEmail");
        q.setParameter(1, s);
        System.out.println("Dal database prelevo un istanza di tipo"+  q.getSingleResult());
        return (Utente) q.getSingleResult();
    }
    
    @Override
    public Amministratore ottieniAmministratore (Utente u) {
        
        Query q = em.createNamedQuery("Utente.isRegistrato");
        q.setParameter(1, u.getUsername());
        System.out.println("Dal database prelevo un istanza di tipo"+  q.getSingleResult());
        return (Amministratore) q.getSingleResult();
    }
    
    @Override
    public GestoreMagazzino ottieniGestoreMagazzino (Utente u) {
        
        Query q = em.createNamedQuery("Utente.isRegistrato");
        q.setParameter(1, u.getUsername());
        System.out.println("Dal database prelevo un istanza di tipo"+  q.getSingleResult());
        return (GestoreMagazzino) q.getSingleResult();
    }
    
    @Override
    public boolean esisteUsername (String username) {
        Query q = em.createNamedQuery("Utente.isRegistrato");
        q.setParameter(1, username);
        if (!q.getResultList().isEmpty()) {
            return true;
        }
        return false;
    }
    
    @Override    
    public Cliente cercaPerEmail(String email) {
        if (email == null) {

            throw new NullPointerException();
        }

        Query q = em.createNamedQuery("ClienteEmail");
        q.setParameter(1, email);

        return (Cliente) q.getSingleResult();
    }
    
    @Override    
    public Cliente cercaPerID (Long id) {
        if (id == null) {

            throw new NullPointerException();
        }

        Query q = em.createNamedQuery("ClienteID");
        q.setParameter(1, id);

        return (Cliente) q.getSingleResult();
    }
    
     @Override
    public void modificaPassword(String newPassword, String username) throws AccountException {
        if (esisteUsername (username)) {
            Utente a = ottieniUtente(username);
            System.out.println("Password nuova"+newPassword);
            try {
                a.setPassword(newPassword);
            } catch (Exception ex) {
                
                return;
            }
            utenteFacade.edit(a);
            System.out.println("Password modificata");
        } else {
            throw new AccountException();
        }
    }

}
