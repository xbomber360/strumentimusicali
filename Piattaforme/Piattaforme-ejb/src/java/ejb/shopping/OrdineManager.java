/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;


import entity.Ordine;
import entity.TipoSpedizione;
import entity.Utente;
import facade.OrdineFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import manager.StatoOrdini;

/**
 *
 * @author siciliano
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrdineManager implements OrdineManagerLocal {
    
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    @EJB
    private OrdineFacadeLocal of;
   
    //L'ORDINE VIENE CREATO E SETTATO CON I VALORI DAL CARRELLO,  E POI VIENE PASSATO A QUESTO METODO PER LA CREAZIONE VERA E PROPRIA 
    @Override
    public void creaOrdine(Ordine o) {
        if (o == null)
            throw new IllegalArgumentException();
        of.create(o);
        System.out.println("[OrdineManager] Ordine creato con successo");

    }
    
    //TUTTI I VALORI DELL'ORDINE VENGONO SETTATI DAL BEAN CARRELLO
    //@Override
    //public void creaOrdine(Long idUtente , List<OggettoOrdinato> carrello, TipoSpedizione spedizione) {
      //  Ordine o = new Ordine();
        //Date dataAcquisto = new Date(new GregorianCalendar().getTimeInMillis());
        //o.setDataOrdine(dataAcquisto);
        //o.setStato(StatoOrdini.inLavorazione);
        //o.setListaOggettiOrdinati(carrello);
        //o.setTipoSpedizione(spedizione);
        //o.setTotale(totale);
        //Fattura f = new Fattura();
        //f.setData(dataAcquisto);
        //f.setDettaglio("L'ordine e' stato acquistato il giorno " + dataAcquisto.toString() + ". I prodotti da lei acquistati sono : " + carrello.toString()); // da gestire meglio 
        //of.create(o);
        //System.out.println("[OrdineManager] Ordine creato con successo");

    public void persist(Object object) {
        em.persist(object);
    }
        
        
        
    

    @Override
    public void rimuoviOrdine(Ordine o ) {
        if (o == null)
            return;
        Query q = em.createNamedQuery("ordine.cercaOrdinePerId");
        q.setParameter(1, o.getId());
        if (q.getResultList().isEmpty()) {
            System.out.println("[OrdineManager] Impossibile eliminare l'ordine numero "+ o.getId() + " ordine non trovato" );
            return;
        }
        of.remove(o); 
        System.out.println("[OrdineManager] L'ordine numero "+ o.getId() + " è stato rimosso con successo" );
        
    }

    @Override
    public void modificaStatoOrdine(Ordine o, StatoOrdini s) {
            
         Ordine temp = of.find(o.getId());
         if(temp==null){
            System.out.println("[OrdineManager] Impossibile modificare lo stato dell'ordine numero "+ o.getId() + " non è stato trovato" ); 
            return;
         }
         temp.setStato(s);
         of.edit(temp);
        System.out.println("[OrdineManager] Lo stato dell'ordine numero "+ o.getId() + " è stato modificato in " + s ); 
    }

    @Override
    public List<Ordine> cercaTuttiGliOrdini() {
        List<Ordine> res = of.findAll();
        if(res==null){
           System.out.println("[OrdineManager] Non sono presenti ordini" ); 
        }
        return res;
    }
    @Override
    public List<Ordine> cercaTuttiGliOrdiniUtente(Utente u){
        //DA VEDERE COME GESTISCE FRANCESCO LA PROMOZIONE IN CASO USARE LA USER INVECE DELL'ID , va cambiata anche la namedQuery nell'entity
        Query q = em.createNamedQuery("ordine.cercaOrdiniUtente");
        q.setParameter(1, u.getId());
        List<Ordine> res = q.getResultList();
        if(res==null){
            System.out.println("[OrdineManager] Non sono presenti ordini dell'utente " + u.getId() );
        }
        return res;
    }
    
    @Override
    public Float cercaPrezzoSpedizione(TipoSpedizione spese) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
