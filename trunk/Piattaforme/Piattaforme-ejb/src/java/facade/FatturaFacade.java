/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Fattura;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maidenfp
 */
@Stateless
public class FatturaFacade extends AbstractFacade<Fattura> {
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FatturaFacade() {
        super(Fattura.class);
    }
    
}
