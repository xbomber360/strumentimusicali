/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Cliente;
import entity.Utente;
import facade.UtenteFacade;
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
    private UtenteFacade utenteFacade;
    
    @Override
    public boolean ePresente(Cliente c) {
        Query q = em.createNamedQuery("cercaUtentePerEmail");
        q.setParameter(1, c.getEmail());
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
    public void rimuoviCliente(Cliente c) {
        utenteFacade.remove(c);
    }

}
