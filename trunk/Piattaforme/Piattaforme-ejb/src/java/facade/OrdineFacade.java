/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Ordine;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Lorenzo
 */
@Stateless
public class OrdineFacade extends AbstractFacade<Ordine> {
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdineFacade() {
        super(Ordine.class);
    }
    
}
