/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.gestoremagazzino;

import facade.GestoreMagazzinoFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author maidenfp
 */
@Stateful
public class GestoreMagazzino implements GestoreMagazzinoLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Long id;
    private String nome;  
    @EJB
    private GestoreMagazzinoFacadeLocal gestore;
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
     @Override
    public List <entity.GestoreMagazzino> cercaTuttiGestori() {
        List<entity.GestoreMagazzino> res = gestore.findAll();
        if(res==null){
           System.out.println("[AmministratoreManager] Non sono presenti amministratori" ); 
        }
        return res;
    }
    
      @Override
    public void rimuoviGestori(entity.GestoreMagazzino gm ) {
        if (gm == null)
            return;
        Query q = em.createNamedQuery("EsisteGestore");
        q.setParameter(1, gm.getId());
        if (q.getResultList().isEmpty()) {
            System.out.println("[GestoreManager] Impossibile eliminare gestore "+ gm.getId() + " gestoree non trovato" );
            return;
        }
        gestore.remove(gm); 
        System.out.println("[GestoreManager] Il gestore numero "+gm.getId() + " è stato rimosso con successo" );
        
    }
}
