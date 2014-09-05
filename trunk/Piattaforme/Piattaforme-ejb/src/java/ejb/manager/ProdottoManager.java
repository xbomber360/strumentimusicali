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
    @EJB
    private CategoriaManagerLocal cm;
    @EJB
    private MarcaManagerLocal mm;
    
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public boolean controllaDisponibilita(Long idProdotto, int quantita) {

        Prodotto p = pf.find(idProdotto);
        if (p == null) {
            System.out.println("[ProdottoManager] Impossibile controllare la quantita del prodotto con id " +idProdotto + " prodotto non trovato.");
            return false;
        }

        return p.getQuantita() >= quantita;
    }

   

    @Override
    public void aggiungiCategoria(Categoria c) {
        cm.creaCategoria(c);
    }

    @Override
    public void aggiungiMarca(Marca m) {
        mm.creaMarca(m);
    }

    @Override
    public void aggiungiProdotto(Prodotto p) {
        Query q = em.createNamedQuery("prodotto.cercaProdottoPerNome");
        q.setParameter(1, p.getNome());
        if (!q.getResultList().isEmpty()) {
            System.out.println("[ProdottoManager] Impossibile inserire il prodotto con " +p.getNome() + " prodotto già esistente.");
            return;
            
        }
        pf.create(p);
        System.out.println("[ProdottoManager] Prodotto inserito con successo");
        
    }
    
    @Override
    public void aggiungiQuantitaProdotto(Prodotto p, int quantita){ //Metodo usato dal carrello per aggiungere prodotti al magazzino quando si rimuove un prodotto dal carrello
        //Il valore in input viene sommato alla quantita del prodotto
         Prodotto temp = pf.find(p.getId());
        if(temp==null){
         System.out.println("[ProdottoManager] Impossibile aggiungere la quantita del prodotto con nome " +p.getNome() + " prodotto non trovato.");
         return;
        }
        int quantitaModificata = temp.getQuantita() + quantita;
        temp.setQuantita(quantitaModificata);
        pf.edit(temp);
        System.out.println("[ProdottoManager] Aggiunta quantità prodotto, nuova quantita= " +quantitaModificata);
    }
    @Override
    public void rimuoviQuantitaProdotto(Prodotto p , int quantita){ //Metodo usato dal carrello per rimuovere prodotti al magazzino quando si aggiunge un prodotto dal carrello
        //Il valore in input viene sommato alla quantita del prodotto
        Prodotto temp = pf.find(p.getId());
        if(temp==null){
         System.out.println("[ProdottoManager] Impossibile rimuovere la quantita del prodotto con nome " +p.getNome() + " prodotto non trovato.");
         return;
        }
        int quantitaModificata = temp.getQuantita() - quantita;
        if(quantitaModificata<0){
            System.out.println("[ProdottoManager] La quantita del prodotto con nome " +p.getNome() + " è in negativo");
            quantitaModificata=0;
        }
        temp.setQuantita(quantitaModificata);
        pf.edit(temp);
        System.out.println("[ProdottoManager] Rimossa quantità prodotto, nuova quantita= " +quantitaModificata);
    }
    
     @Override
    public void aggiungiQuantitaProdotto(Long idProdotto, int quantita){ //Metodo usato dal carrello per aggiungere prodotti al magazzino quando si rimuove un prodotto dal carrello
        //Il valore in input viene sommato alla quantita del prodotto
         Prodotto temp = pf.find(idProdotto);
        if(temp==null){
         System.out.println("[ProdottoManager] Impossibile aggiungere la quantita del prodotto con nome " +temp.getNome() + " prodotto non trovato.");
         return;
        }
        int quantitaModificata = temp.getQuantita() + quantita;
        temp.setQuantita(quantitaModificata);
        pf.edit(temp);
        System.out.println("[ProdottoManager] Aggiunta quantità prodotto, nuova quantita= " +quantitaModificata);
    }
    @Override
    public void rimuoviQuantitaProdotto(Long idProdotto , int quantita){ //Metodo usato dal carrello per rimuovere prodotti al magazzino quando si aggiunge un prodotto dal carrello
        //Il valore in input viene sommato alla quantita del prodotto
        Prodotto temp = pf.find(idProdotto);
        if(temp==null){
         System.out.println("[ProdottoManager] Impossibile rimuovere la quantita del prodotto con nome " +temp.getNome() + " prodotto non trovato.");
         return;
        }
        int quantitaModificata = temp.getQuantita() - quantita;
        if(quantitaModificata<0){
            System.out.println("[ProdottoManager] La quantita del prodotto con nome " +temp.getNome() + " è in negativo");
            quantitaModificata=0;
        }
        temp.setQuantita(quantitaModificata);
        pf.edit(temp);
        System.out.println("[ProdottoManager] Rimossa quantità prodotto, nuova quantita= " +quantitaModificata);
    }

    @Override
    public void modificaQuantitaProdotto(Prodotto p, int quantita) { //Metodo usato dal gestore magazzino per modificare la quantita di prodotto
        //Il valore in input è la nuova quantità
        Prodotto temp = pf.find(p.getId());
        if(temp==null){
         System.out.println("[ProdottoManager] Impossibile modificare la quantita del prodotto con nome " +p.getNome() + " prodotto non trovato.");
         return;
        }
        temp.setQuantita(quantita);
        pf.edit(temp);
        System.out.println("[ProdottoManager] Modificata quantità prodotto, nuova quantita= " +quantita);

    }
    
    
    @Override
    public void modificaQuantitaProdotto(Long idProdotto, int quantita) {  //Metodo usato dal gestore magazzino per modificare la quantita di prodotto
        //Il valore in input è la nuova quantità
        Prodotto p = pf.find(idProdotto);
        if(p==null){
         System.out.println("[ProdottoManager] Impossibile modificare la quantita del prodotto con id " +idProdotto + " prodotto non trovato.");
         return;
        }
        //int quantitaModificata = p.getQuantita()+quantita; DA DEANNOTARE SE si vuole che la quantita in input venga aggiunta gia a quella presente
        p.setQuantita(quantita);
        pf.edit(p);
        System.out.println("[ProdottoManager] Modificata quantità prodotto, nuova quantita= " +p.getQuantita());
    }


    @Override
    public void rimuoviCategoria(Categoria c) {
        cm.rimuoviCategoria(c);
    }

    @Override
    public void rimuoviProdotto(Prodotto p) {
        Prodotto temp = pf.find(p.getId());
        if(temp==null){
         System.out.println("[ProdottoManager] Impossibile eliminare il prodotto "+ p.getNome() + " prodotto non trovato");   
         return;
        }
        pf.remove(p);
        System.out.println("[ProdottoManager] I prodotto "+ p.getNome() + " è stato rimosso con successo");
        
    }
    
     @Override
    public Prodotto cercaProdottoPerId(Long idProdotto) {
        return pf.find(idProdotto);
    }

    @Override
    public Prodotto cercaProdottoPerNome(String parameter) {
        Query q = em.createNamedQuery("prodotto.cercaProdottoPerNome");
        q.setParameter(1, parameter);
        List<Prodotto> res = q.getResultList();
        if (res.isEmpty()) {
            System.out.println("[ProdottoManager] Prodotto non trovato con il nome "+ parameter);
            return null;
        }
        return res.get(0);
    }

    @Override
    public List<Prodotto> cercaTuttiProdotti() {
        Query q = em.createNamedQuery("prodotto.cercaTuttiProdotti");
        List<Prodotto> res = q.getResultList();
        if(res.isEmpty()){
        System.out.println("[ProdottoManager] Non sono presenti prodotti");
        return null;
        }
        return res;
    }

    @Override
    public List<Prodotto> cercaProdottiPerMarca(String marca) {
        Query q = em.createNamedQuery("prodotto.cercaTuttiProdottiDellaMarca");
        q.setParameter(1, marca);
        List<Prodotto> res = q.getResultList();
        if(res.isEmpty()){
        System.out.println("[ProdottoManager] Non sono presenti prodotti della marca " +marca);
        return null;
        }
        return res;
    }

    @Override
    public List<Prodotto> cercaProdottiPerMarcaCategoria( Long idMarca,Long idCategoria ){
        Query q = em.createQuery("SELECT p FROM Prodotto p WHERE p.categoria.id=?1 AND p.marca.id=?2");
        q.setParameter(1, idCategoria);
        q.setParameter(2, idMarca);
        List<Prodotto> res = q.getResultList();
         if(res.isEmpty()){
        System.out.println("[ProdottoManager] Non sono presenti prodotti della marca con idMarca " +idMarca+" e idCategoria " + idCategoria);
        return null;
        }
        return res;
       
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
    @Override
    public List<Prodotto> prodottiDaUnSet(java.util.Set<Long> codiceBarre) {
        if(codiceBarre==null){
            System.out.println("[ProdottoManager] Impossibile cercare prodotti da un set , il set è null");
            return null;
        }
        if(codiceBarre.isEmpty()){
                        System.out.println("[ProdottoManager] Impossibile cercare prodotti da un set , il set è vuoto");

            return null;
        }
        Query q = em.createNamedQuery("prodotto.prodottiDaUnSet");
        q.setParameter("lista", codiceBarre);
        List<Prodotto> res= q.getResultList() ;
        System.out.println("[ProdottoManager] i prodotti da un set sono " + res);
        return res;
    }

    @Override
    public List<String> cercaPattern(String query) {
         Query q = em.createQuery("SELECT p.nome FROM Prodotto p WHERE p.nome LIKE ?1");
        q.setParameter(1, query+"%");
        return q.getResultList();
    }

    @Override
    public boolean isPresenteProdottoDellaMarca(Marca m){
        Query q = em.createNamedQuery("prodotto.cercaTuttiProdottiDellaMarca");
        q.setParameter(1, m.getId());
        List<Marca> res = q.getResultList();
        return !res.isEmpty();
        
    }
    @Override
    public boolean isPresenteProdottoDellaCategoria(Categoria c){
         Query q = em.createNamedQuery("prodotto.cercaTuttiProdottiDellaCategoria");
        q.setParameter(1, c.getId());
        List<Marca> res = q.getResultList();
        return !res.isEmpty();
    }
    
    
    
}
