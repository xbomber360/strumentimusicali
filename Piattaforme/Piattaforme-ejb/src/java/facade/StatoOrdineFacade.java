/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.StatoOrdine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maidenfp
 */
@Stateless
public class StatoOrdineFacade extends AbstractFacade<StatoOrdine> {
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatoOrdineFacade() {
        super(StatoOrdine.class);
    }
    
}
