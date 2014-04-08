/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.GestoreMagazzino;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maidenfp
 */
@Stateless
public class GestoreMagazzinoFacade extends AbstractFacade<GestoreMagazzino> implements GestoreMagazzinoFacadeLocal {
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GestoreMagazzinoFacade() {
        super(GestoreMagazzino.class);
    }
    
}
