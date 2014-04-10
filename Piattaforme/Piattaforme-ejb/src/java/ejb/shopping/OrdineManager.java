/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import classi.OggettoOrdinato;
import entity.Fattura;
import entity.Ordine;
import entity.Spedizione;
import entity.TipoSpedizione;
import facade.FatturaFacade;
import facade.OrdineFacade;
import facade.SpedizioneFacade;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import manager.StatoOrdini;

/**
 *
 * @author siciliano
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OrdineManager implements OrdineManagerLocal {
    
    
    EntityManager em;
    @EJB
    private OrdineFacade of;
    @EJB
    private FatturaFacade ff;
    @EJB
    private SpedizioneFacade spef;

    @Override
    public void creaOrdine(Ordine o) {
        if (o == null)
            throw new IllegalArgumentException();
        of.create(o);
    }

    @Override
    public void creaOrdine(Long idUtente , List<OggettoOrdinato> carrello, Spedizione spedizione) {
        Ordine o = new Ordine();
        java.util.Date d = GregorianCalendar.getInstance().getTime();
        Date dataAcquisto = new Date(new GregorianCalendar().getTimeInMillis());
        o.setDataOrdine(dataAcquisto);
        o.setStato(StatoOrdini.inLavorazione);
        o.setListaOggettiOrdinati(carrello);
        Fattura f = new Fattura();
        f.setData(dataAcquisto);
        f.setDettaglio("L'ordine e' stato acquistato il giorno " + dataAcquisto.toString() + ". I prodotti da lei acquistati sono : " + carrello.toString()); // da gestire meglio 
        o.setSpedizione(spedizione);
        
        
        
        
    }

    @Override
    public void rimuoviOrdine(Ordine o ) {
        if (o == null)
            return;
        Query q = em.createNamedQuery("ordine.cercaOrdinePerId");
        q.setParameter(1, o.getId());
        if (!q.getResultList().isEmpty()) {

            return;
        }
        of.remove(o); 
        
    }

    @Override
    public void modificaStatoOrdine(Ordine o, StatoOrdini s) {
        
         if (o == null) 
              throw new IllegalArgumentException("L'ordine è null");
         o.setStato(s);
         of.edit(o);
        
    }

    @Override
    public void aggiungiSpedizione(Ordine o, Spedizione s) {
        
        if (o == null) 
              throw new IllegalArgumentException("L'ordine è null");
         o.setSpedizione(s);
         of.edit(o);
    }

    @Override
    public void aggiungiFattura(Ordine o, Fattura f) {
        if (o == null) 
              throw new IllegalArgumentException("L'ordine è null");
         o.setFattura(f);
         of.edit(o);
    }

    @Override
    public void rimuoviFattura(Ordine o) {
        if (o == null) 
              throw new IllegalArgumentException("L'ordine è null");
         o.setFattura(null);
         of.edit(o);
    }

    @Override
    public void rimuoviSpedizione(Ordine o) {
        if (o == null) 
              throw new IllegalArgumentException("L'ordine è null");
         o.setSpedizione(null);
         of.edit(o);
    }

    @Override
    public void creaFattura(Fattura f) {
        
    }

    @Override
    public Float cercaPrezzoSpedizione(TipoSpedizione spese) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

}
