/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.manager;

import entity.Categoria;
import entity.Marca;
import entity.Prodotto;
import exception.ProdottoNonTrovatoException;
import facade.ProdottoFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author maidenfp
 */
@Stateless
public class ProdottoManager implements ProdottoManagerLocal {
    @EJB
    private ProdottoFacadeLocal pf;
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean controllaQuantita(Long idProdotto, int quantita) throws ProdottoNonTrovatoException {

        Prodotto p = pf.find(idProdotto);
        if (p == null) {
            throw new ProdottoNonTrovatoException();
        }

        return p.getQuantita() >= quantita;
    }

   

    @Override
    public void aggiungiCategoria(Categoria c) {
    }

    @Override
    public void aggiungiMarca(Marca m) {
    }

    @Override
    public void aggiungiProdotto(Prodotto p, Marca m, Categoria c) {
    }

    @Override
    public void modificaQuantitaProdotto(Prodotto p, int quantita) {
    }
    
    

    @Override
    public void rimuoviCategoria(Categoria c) {
    }

    @Override
    public void rimuoviProdotto(Prodotto p) {
        
        
    }
    
     @Override
    public Prodotto cercaProdottoPerId(Long idProdotto) {
        return null;
    }

    @Override
    public List<Prodotto> cercaProdottoPerNome(String parameter) {
        return null;
    }

    @Override
    public List<Prodotto> cercaTuttiProdotti() {
        return null;
    }

    @Override
    public List<Prodotto> cercaProdottiPerMarca(String marca) {
        return null;
    }

    @Override
    public void modificaQuantitaProdottoId(Long idProdotto, int quantita) {
    }
    
    
    @Override
    public List<Prodotto> cercaProdottiPerMarcaCategoria( Long idMarca,Long idCategoria ){
        Query q = em.createQuery("SELECT p FROM Prodotto p WHERE p.categoria.id=?1 AND p.marca.id=?2");
        q.setParameter(1, idCategoria);
        q.setParameter(2, idMarca);
        return q.getResultList();
       
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    
    

    
}
