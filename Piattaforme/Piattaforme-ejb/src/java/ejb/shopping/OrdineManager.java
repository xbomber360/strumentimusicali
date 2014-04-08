/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import entity.Ordine;
import facade.FatturaFacade;
import facade.OrdineFacade;
import facade.SpedizioneFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;

/**
 *
 * @author siciliano
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrdineManager implements OrdineManagerLocal {
    
    
    EntityManager em;
    @EJB
    private OrdineFacade om;
    @EJB
    private FatturaFacade ff;
    @EJB
    private SpedizioneFacade spef;

    @Override
    public void creaOrdine(Ordine o) {
    }
    
    
    
    

}
