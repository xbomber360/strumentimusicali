/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.manager;

import entity.Categoria;
import entity.Marca;
import facade.*;
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
public class CategoriaManager implements CategoriaManagerLocal {

    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    @EJB
    private CategoriaFacadeLocal categoriaFacade;
    
    @Override
    public void creaCategoria(Categoria cat) {
        Query q = em.createNamedQuery("categoria.cercaPerNome");
        q.setParameter(1, cat.getNome());
        if (!q.getResultList().isEmpty()) {
            System.out.println("[Categoria Manager] Impossibile inserire la Categoria con il nome: " + cat.getNome()+" categoria già esistente");
            return;
        }
        categoriaFacade.create(cat);
        System.out.println("[Categoria Manager] Creata nuova categoria di nome: " + cat.getNome());
    }

    @Override
    public Categoria cercaPerNome(String nome) {
        Query q = em.createNamedQuery("categoria.cercaPerNome");
        q.setParameter(1, nome);
        List<Categoria> res = q.getResultList();
        if (res.isEmpty()) {
            System.out.println("[Categoria Manager] Categoria non trovata con il nome: "+ nome);
            return null;
        }
        return res.get(0);
    }
    
    @Override
    public Categoria cercaPerId(Long id){
        Query q = em.createNamedQuery("categoria.cercaPerId");
        q.setParameter(1, id);
        List<Categoria> res = q.getResultList();
        if (res.isEmpty()) {
            System.out.println("[Categoria Manager] Categoria non trovata con l'id: "+ id);
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<Categoria> cercaTutto() {
        List<Categoria> res = categoriaFacade.findAll();
        
        if(res==null){
            System.out.println("[CategoriaManager] Non sono presenti categorie");
        }
        return res;
    }

    @Override
    public void modificaCategoria(Categoria cat) {
        categoriaFacade.edit(cat);
    }

    @Override
    public void rimuoviCategoria(Categoria cat) {
      categoriaFacade.remove(cat);
    }

    @Override
    public List<Marca> getMarcheCategoria(Long idCategoria) {
        if(idCategoria!=null){
        Query q = em.createQuery("SELECT m FROM Marca m WHERE EXISTS (SELECT p FROM Prodotto p, Categoria c WHERE p.categoria = c AND c.id=?1 AND m=p.marca)");
        q.setParameter(1, idCategoria);
        return q.getResultList();
        }
        return null;
    }

    @Override
    public List<String> cercaPattern(String query) {
        Query q = em.createQuery("SELECT c.nome FROM Categoria c WHERE c.nome LIKE ?1");
        q.setParameter(1, query+"%");
        return q.getResultList();
    }
}
