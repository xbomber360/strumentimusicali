/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Marca;
import facade.MarcaFacade;
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
    private MarcaFacade marcaFacade;

    @Override
    public void creaMarca (Marca m) {
        Query q = em.createNamedQuery("marca.cercaMarcaPerNome");
        q.setParameter(1, m.getNome());
        if (!q.getResultList().isEmpty()) {
            return;
        }
        marcaFacade.create(m);
    }

    @Override
    public Marca cercaPerNome(String nome) {
        Query q = em.createNamedQuery("marca.cercaMarcaPerNome");
        q.setParameter(1, nome);
        List<Marca> res = q.getResultList();
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

       @Override
    public List<Marca> cercaTutto() {
        List<Marca> res = marcaFacade.findAll();
        return res;
    }

    @Override
    public void modificaMarca(Marca m) {
        marcaFacade.edit(m);
    }

    @Override
    public void rimuoviMarca(Marca m) {
        marcaFacade.remove(m);
    }
}