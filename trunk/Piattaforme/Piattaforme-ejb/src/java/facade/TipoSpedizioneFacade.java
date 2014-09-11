/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.TipoSpedizione;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author siciliano
 */
@Stateless
public class TipoSpedizioneFacade extends AbstractFacade<TipoSpedizione> implements TipoSpedizioneFacadeLocal {
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoSpedizioneFacade() {
        super(TipoSpedizione.class);
    }
    
}
