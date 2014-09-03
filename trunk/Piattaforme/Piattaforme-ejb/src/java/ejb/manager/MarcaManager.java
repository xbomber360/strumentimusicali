/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Marca;
import facade.MarcaFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author maidenfp
 */

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MarcaManager implements MarcaManagerLocal {
@PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    @EJB
    private MarcaFacadeLocal marcaFacade;

    @Override
    public void creaMarca (Marca m) {
        Query q = em.createNamedQuery("marca.cercaMarcaPerNome");
        q.setParameter(1, m.getNome());
        if (!q.getResultList().isEmpty()) {
            System.out.println("[MarcaManager] Impossibile inserire la marca con il nome " + m.getNome()+" marca già esistente");
            return;
        }
        marcaFacade.create(m);
        System.out.println("[MarcaManager] Creata nuova marca con il nome " + m.getNome());
    }

    @Override
    public Marca cercaPerNome(String nome) {
        Query q = em.createNamedQuery("marca.cercaMarcaPerNome");
        q.setParameter(1, nome);
        List<Marca> res = q.getResultList();
        if (res.isEmpty()) {
            System.out.println("[MarcaManager] Marca non trovata con il nome "+ nome);
            return null;
        }
        return res.get(0);
    }

       @Override
    public List<Marca> cercaTutto() {
        List<Marca> res = marcaFacade.findAll();
        if(res==null){
            System.out.println("[MarcaManager] Non sono presenti marche");
        }
        return res;
    }

    @Override
    public void modificaMarca(Marca m) {
        Marca temp = marcaFacade.find(m.getId());
        if(temp==null){
            System.out.println("[MarcaManager] Impossibile modificare la marca  con il nome "+ m.getNome()+" marca non trovata");
            return;
        }
        marcaFacade.edit(m);
        System.out.println("[MarcaManager] La marca  con il nome "+ m.getNome()+" è stata modificata con successo");

    }

    @Override
    public void rimuoviMarca(Marca m) {
        Marca temp = marcaFacade.find(m.getId());
        if(temp==null){
            System.out.println("[MarcaManager] Impossibile rimuovere la marca  con il nome "+ m.getNome()+" marca non trovata");
            return;
        }
        marcaFacade.remove(m);
        System.out.println("[MarcaManager] La marca  con il nome "+ m.getNome()+" è stata rimossa con successo");

    }

    @Override
    public List<String> cercaPattern(String query) {
        Query q = em.createQuery("SELECT m.nome FROM Marca m WHERE m.nome LIKE ?1");
        q.setParameter(1, query+"%");
        return q.getResultList();
    }
    
    
}