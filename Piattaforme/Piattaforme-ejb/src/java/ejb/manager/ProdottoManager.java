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
import facade.CategoriaFacadeLocal;
import facade.MarcaFacadeLocal;
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
    @EJB
    private CategoriaFacadeLocal cf;
    @EJB
    private MarcaFacadeLocal mf;
    
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
        cf.create(c);
    }

    @Override
    public void aggiungiMarca(Marca m) {
        mf.create(m);
    }

    @Override
    public void aggiungiProdotto(Prodotto p) {
        pf.create(p);
        
    }

    @Override
    public void modificaQuantitaProdotto(Prodotto p, int quantita) {
        Prodotto temp = pf.find(p.getId());
        temp.setQuantita(quantita);
        pf.edit(temp);
    }
    
    

    @Override
    public void rimuoviCategoria(Categoria c) {
        cf.remove(c);
    }

    @Override
    public void rimuoviProdotto(Prodotto p) {
        pf.remove(p);
        
    }
    
     @Override
    public Prodotto cercaProdottoPerId(Long idProdotto) {
        return pf.find(idProdotto);
    }

    @Override
    public Prodotto cercaProdottoPerNome(String parameter) {
        Query q = em.createNamedQuery("Prodotto.cercaProdottoPerNome");
        q.setParameter(1, parameter);
        List<Prodotto> res = q.getResultList();
        if (res.isEmpty()) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<Prodotto> cercaTuttiProdotti() {
        Query q = em.createNamedQuery("Prodotto.cercaTuttiProdotti");
        List<Prodotto> res = q.getResultList();
        if(res.isEmpty()){
        return null;
        }
        return res;
    }

    @Override
    public List<Prodotto> cercaProdottiPerMarca(String marca) {
        Query q = em.createNamedQuery("Prodotto.cercaTuttiProdottiDellaMarca");
        q.setParameter(1, marca);
        List<Prodotto> res = q.getResultList();
        if(res.isEmpty()){
        return null;
        }
        return res;
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
    
    @Override
    public List<Prodotto> prodottiDaUnSet(java.util.Set<Long> codiceBarre) {
        if(codiceBarre==null){
            return null;
        }
        if(codiceBarre.isEmpty()){
            return null;
        }
        Query q = em.createNamedQuery("prodotto.prodottiDaUnSet");
        q.setParameter("lista", codiceBarre);
        return q.getResultList();
    }
    
}
