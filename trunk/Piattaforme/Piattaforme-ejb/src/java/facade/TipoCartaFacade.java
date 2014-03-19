/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.TipoCarta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maidenfp
 */
@Stateless
public class TipoCartaFacade extends AbstractFacade<TipoCarta> {
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoCartaFacade() {
        super(TipoCarta.class);
    }
    
}
