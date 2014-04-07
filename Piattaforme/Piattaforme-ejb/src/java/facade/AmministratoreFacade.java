/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Amministratore;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maidenfp
 */
@Stateless
public class AmministratoreFacade extends AbstractFacade<Amministratore> {
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AmministratoreFacade() {
        super(Amministratore.class);
    }
    
}