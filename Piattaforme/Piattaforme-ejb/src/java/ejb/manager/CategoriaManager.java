/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Categoria;
import facade.CategoriaFacade;
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
    private CategoriaFacade categoriaFacade;

    @Override
    public void creaCategoria(Categoria cat) {
        Query q = em.createNamedQuery("marchio.cercaPerNome");
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
    public List<Categoria> cercaTutto() {
        List<Categoria> res = categoriaFacade.findAll();
        return res;
    }

    @Override
    public void modificaMarchio(Categoria cat) {
        categoriaFacade.edit(cat);
    }

    @Override
    public void rimuoviMarchio(Categoria cat) {
        categoriaFacade.remove(cat);
    }
}