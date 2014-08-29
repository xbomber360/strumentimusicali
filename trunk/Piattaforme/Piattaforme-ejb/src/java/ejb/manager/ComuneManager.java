/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Comune;
import entity.Provincia;
import facade.ComuneFacadeLocal;
import facade.ProvinciaFacadeLocal;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author maidenfp
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ComuneManager implements ComuneManagerLocal {
    @EJB
    private ComuneFacadeLocal comuneFacade;
    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    

    @Override
    public List<Comune> trovaDaProvincia(Provincia provincia) {
       if(provincia==null){
           System.out.println("Provincia non valida");
           return new LinkedList<Comune>();
       }
       Query q=em.createNamedQuery("trovaComuniConProvincia");
       q.setParameter(1, provincia);
       List<Comune> res = q.getResultList();
       return res;
    }

    @Override
    public List<Comune> trovaDaIdProvincia(Long idProvincia) {
        if(idProvincia==null || idProvincia<0){
            return new LinkedList<Comune>();
        }
        Provincia p=provinciaFacade.find(idProvincia);
        return this.trovaDaProvincia(p);
    }

    @Override
    public Comune trovaDaId(Long id) {
        return comuneFacade.find(id);
    }
    

}
