/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import classi.OggettoOrdinato;
import ejb.manager.ClienteManagerLocal;
import ejb.manager.FatturaManagerLocal;
import ejb.manager.ProdottoManagerLocal;
import ejb.manager.SpedizioneManagerLocal;
import entity.Cliente;
import entity.Ordine;
import entity.Spedizione;
import entity.TipoSpedizione;
import exception.ClienteNonPresenteException;
import exception.ProdottoNonTrovatoException;
import exception.ProdottoQuantitaException;
import java.io.Serializable;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import manager.StatoOrdini;

/**
 *
 * @author siciliano
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Carrello implements CarrelloLocal,Serializable {

    @EJB
    private OrdineManagerLocal om;
    @EJB
    private ProdottoManagerLocal pm;
    @EJB
    private ClienteManagerLocal cm;
    @EJB
    private SpedizioneManagerLocal sm;
    @EJB
    private FatturaManagerLocal fm;
    
    private Map<Long, OggettoOrdinato> carrello;
    
    private Float subTotale; 
 
    @PreDestroy
    protected void preDestroy() {
       this.carrello.clear();
        subTotale= new Float(0.00);
    }

    @PostConstruct
    protected void init() {
        this.carrello = new HashMap<Long,OggettoOrdinato>();
        this.subTotale = new Float(0.00);
    }
    
    @Override
    public void aggiungiProdottoAlCarrello(Long idProdotto , int quantita)throws ProdottoNonTrovatoException,ProdottoQuantitaException {
        if (pm.controllaQuantita(idProdotto, quantita)){
            OggettoOrdinato o = new OggettoOrdinato();
            o.setIdProdotto(idProdotto);
            o.setQuantita(quantita);
            subTotale+= pm.cercaProdottoPerId(idProdotto).getPrezzo()*quantita;
            
        }//if
        else
            throw new ProdottoQuantitaException();
        
    }

    @Override
    public void rimuoviProdottoDalCarrello(Long idprodotto) {
        if(carrello.get(idprodotto) == null)
            return;
        OggettoOrdinato o = carrello.remove(idprodotto);
        subTotale-= pm.cercaProdottoPerId(o.getIdProdotto()).getPrezzo()*o.getQuantita();
        pm.modificaQuantitaProdottoId(idprodotto, o.getQuantita());

        
    }

    @Override
    public void aggiungiQuantitaProdotto(Long idProdotto , int quantita) {
       if(carrello.get(idProdotto) == null)
            return;
       carrello.get(idProdotto).setQuantita(carrello.get(idProdotto).getQuantita()+quantita); //non so se va fatta cosi o con la remove
       subTotale+= pm.cercaProdottoPerId(idProdotto).getPrezzo()*quantita;
       pm.modificaQuantitaProdottoId(idProdotto, quantita);


    }//metodo

    @Override
    public void rimuoviQuantitaProdotto(Long idProdotto, int quantita) {
        if(carrello.get(idProdotto) == null)
            return;
       int temp = carrello.get(idProdotto).getQuantita();
       if(temp - quantita == 0){
           carrello.remove(idProdotto);
           pm.modificaQuantitaProdottoId(idProdotto, quantita);

       }else
           if(temp<0)
                   throw new IllegalArgumentException("Quantità non valida da rimuovere");
           else {
                carrello.get(idProdotto).setQuantita(carrello.get(idProdotto).getQuantita()+quantita); //non so se va fatta cosi o con la remove
                pm.modificaQuantitaProdottoId(idProdotto, quantita);

           }//else
        
    }

    @Override
    public void svuotaCarrello() {
        
            
            
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void creaOrdine(Long idCliente, TipoSpedizione sp) throws ClienteNonPresenteException{
        if (carrello.isEmpty()) {
            throw new IllegalStateException("Carrello vuoto o non è stato aggiunto un nuovo prodotto");
        }
        Ordine o = new Ordine();
        Cliente c = cm.cercaPerID(idCliente);
        
        if(c == null)
            throw new ClienteNonPresenteException("Il cliente non e' stato trovato durante la creazione dell'ordine ");
        
        o.setCliente(c);
        o.setTipoSpedizione(sp);
        Date dataOrdine=new Date(new GregorianCalendar().getTimeInMillis());
        o.setDataOrdine(dataOrdine);
        o.setStato(StatoOrdini.inLavorazione);
        LinkedList<OggettoOrdinato> lista = new LinkedList<OggettoOrdinato>;
        
        
        
    }

    @Override
    public Float getTotale(TipoSpedizione spese) {
        return subTotale+sm.cercaPrezzoSpedizione(spese);
   }
    
    
} 
    
    
    
    
    

    

   
    
    

