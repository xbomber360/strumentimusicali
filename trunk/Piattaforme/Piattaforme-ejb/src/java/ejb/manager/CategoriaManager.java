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
            return;
        }
        categoriaFacade.create(cat);
    }

    @Override
    public Categoria cercaPerNome(String nome) {
        Query q = em.createNamedQuery("categoria.cercaPerNome");
        q.setParameter(1, nome);
        List<Categoria> res = q.getResultList();
        if (res.isEmpty()) {
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
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<Categoria> cercaTutto() {
        List<Categoria> res = categoriaFacade.findAll();
        
        //List<Categoria> res = null;
        //Query q = em.createQuery("SELECT c FROM Categoria c");
        //res = q.getResultList();
        return res;
    }

    @Override
    public void modificaCategoria(Categoria cat) {
       // categoriaFacade.edit(cat);
    }

    @Override
    public void rimuoviCategoria(Categoria cat) {
      //  categoriaFacade.remove(cat);
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
}