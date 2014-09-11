/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import ejb.manager.ClienteManagerLocal;
import ejb.manager.ProdottoManagerLocal;
import entity.Fattura;
import entity.OggettoOrdinato;
import entity.Ordine;
import entity.Prodotto;
import entity.TipoSpedizione;
import entity.Utente;
import exception.ClienteNonPresenteException;
import exception.ProdottoNonTrovatoException;
import exception.ProdottoQuantitaException;
import facade.OggettoOrdinatoFacadeLocal;
import facade.UtenteFacadeLocal;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import manager.StatoOrdini;

/**
 *
 * @author siciliano
 */
@Stateful 
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
//@TransactionManagement(TransactionManagementType.CONTAINER)

public class Carrello implements CarrelloLocal,Serializable {
    @EJB
    private OggettoOrdinatoFacadeLocal oggettoOrdinatoFacade;
    @EJB
    private UtenteFacadeLocal utenteFacade;
    @EJB
    private OrdineManagerLocal om;
    @EJB
    private ProdottoManagerLocal pm;
   
    
    
    
    
    
    private Map<Long, OggettoOrdinato> carrello;
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    
    private Float subTotale; 
 
    @PreDestroy
    protected void preDestroy() {
       svuotaCarrello();
        subTotale= new Float(0.00);
    }
    @PostConstruct
    protected void init() {
        this.carrello = new HashMap<Long,OggettoOrdinato>();
        this.subTotale = new Float(0.00);
    }
    
    //public Carrello(){
      //  this.carrello = new HashMap<Long,OggettoOrdinato>();  // Mappa perchè segno l'id del prodotto per facilitarmi alcune operazioni 
        //this.subTotale = new Float(0.00);                   // id dell'oggetto ordinato non lo ho fino a quando l'oggetto non è persistente nel DB 
        
    //}
    
    @Override
    public void aggiungiProdottoAlCarrello(Long idProdotto , int quantita)throws ProdottoNonTrovatoException,ProdottoQuantitaException {
        if (pm.controllaDisponibilita(idProdotto, quantita)){
            pm.rimuoviQuantitaProdotto(idProdotto, quantita); //aggiungendo prodotti nel carrello li rimuovo dal magazzino
            OggettoOrdinato o = new OggettoOrdinato();
            Prodotto p = pm.cercaProdottoPerId(idProdotto);
            o.setProdotto_ordinato(p);
            o.setQuantita(quantita);
            subTotale+= p.getPrezzo()*quantita;
            if(carrello.containsKey(idProdotto)){
                int quantitaModificata = carrello.get(idProdotto).getQuantita() + quantita;
                carrello.get(idProdotto).setQuantita(quantitaModificata);
                System.out.println("[Carrello] Prodotto già presente nel carrello , modificata la quantità , la nuova quantità è : + carrello.get(idProdotto).getQuantita()");
            }
            else{
            carrello.put(idProdotto, o);
            System.out.println("[Carrello]Aggiunto prodotto "+ idProdotto +" quantita: " + quantita+ " subtotale " + subTotale);
                System.out.println("Gli elementi del carrello sono " + carrello.keySet());
            }
        }//if
        else
            System.out.println("[Carrello] Impossibile aggiungere il prodotto nel carrello con id " + idProdotto);
        
    }

    @Override
    public void rimuoviProdottoDalCarrello(Long idProdotto) {
        if(carrello.get(idProdotto) == null){
           System.out.println("[Carrello]Impossibile rimuovere dal carrello il prodotto con id: "+ idProdotto +" , prodotto non trovato ");
            return;
            }
        OggettoOrdinato o = carrello.remove(idProdotto);
        Prodotto temp = pm.cercaProdottoPerId(idProdotto);
        subTotale-= temp.getPrezzo()*o.getQuantita();
        pm.aggiungiQuantitaProdotto(temp, o.getQuantita()); //rimuovendo prodotti dal carrello li rimetto in magazzino
        System.out.println("[Carrello]Rimosso prodotto"+ idProdotto +" quantita: " + o.getQuantita() + " nuovo subtotale " + subTotale);

        
    }
    
    @Override
    public void aggiungiQuantitaProdottoAlCarrello(Long idProdotto , int quantita) { 
       OggettoOrdinato temp =carrello.get(idProdotto); 
       if(temp == null){
           System.out.println("[Carrello]Impossibile aggiungere quantita nel carrello per il prodotto con id: "+ idProdotto +" , prodotto non trovato ");
            return;
       } 
        if (pm.controllaDisponibilita(idProdotto, quantita)){
       int quantitaModificata = temp.getQuantita()+quantita;
       temp.setQuantita(quantitaModificata);
       subTotale+= pm.cercaProdottoPerId(idProdotto).getPrezzo()*quantita;
       pm.rimuoviQuantitaProdotto(idProdotto, quantita); //aggiungendo prodotti nel carrello li rimuovo dal magazzino 
       System.out.println("[Carrello]Aggiunta quantita dal carrello per il prodotto con id: "+ idProdotto +" nuova quantita "+ temp.getQuantita()+ " nuovo subtotale" + subTotale);
        }

    }//metodo

    @Override
    public void rimuoviQuantitaProdottoDalCarrello(Long idProdotto, int quantita) {
        OggettoOrdinato temp =carrello.get(idProdotto); 
        if(temp == null){
            System.out.println("[Carrello]Impossibile rimuovere quantita nel carrello per il prodotto con id: "+ idProdotto +" , prodotto non trovato ");
            return;
        }
       int quantitaModificata = temp.getQuantita() - quantita;
       if(quantitaModificata == 0){
           carrello.remove(idProdotto);
           pm.aggiungiQuantitaProdotto(idProdotto, quantita);//rimuovendo prodotti nel carrello li aggiungo in magazzino
           System.out.println("[Carrello]Quantita da rimuovere superiore a quella del carrello per il prodotto "+ idProdotto +" , rimuovo completamente il prodotto ");
           
       }else
           if(quantitaModificata<0)
                   System.out.println("[Carrello]Quantita da rimuovere superiore a quella del carrello per il prodotto "+ idProdotto +" , rimuovo completamente il prodotto ");
           else {
                temp.setQuantita(quantitaModificata); //non so se va fatta cosi con la replace
                pm.aggiungiQuantitaProdotto(idProdotto, quantita); //rimuovendo prodotti dal carrello li aggiungo in magazzino
                System.out.println("[Carrello]Rimossa quantita dal carrello per il prodotto con id: "+ idProdotto +" nuova quantita "+ temp.getQuantita()+ " nuovo subtotale" + subTotale);

           }//else
        
    }

    @Override
    public void svuotaCarrello() {
        
            Set<Long> listaChiavi= carrello.keySet();
            for (Long chiaveTemp : listaChiavi){
                OggettoOrdinato temp = carrello.get(chiaveTemp);
                pm.aggiungiQuantitaProdotto(temp.getId(), temp.getQuantita());
                
                
            }
            carrello.clear();
            System.out.println("[Carrello]Carrello svuotato correttamente");
                    subTotale= new Float(0.00);

    }
    
     @Override
    public List<Prodotto> getProdotti() {
        return pm.prodottiDaUnSet(carrello.keySet());
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void creaOrdine(Long idCliente, TipoSpedizione sp) throws ClienteNonPresenteException{
        if (carrello.isEmpty()) {
            throw new IllegalStateException("Carrello vuoto o non è stato aggiunto un nuovo prodotto");
        }
        Ordine o = new Ordine();
        Utente u = utenteFacade.find(idCliente);
        
        if(u == null)
            throw new ClienteNonPresenteException("L'utente non e' stato trovato durante la creazione dell'ordine ");
        o.setCliente(u);
        o.setTipoSpedizione(sp);
        Date dataOrdine=new Date(java.util.GregorianCalendar.getInstance().getTimeInMillis());
        o.setDataOrdine(dataOrdine);
        o.setStato(StatoOrdini.Lavorazione);
        LinkedList<OggettoOrdinato> lista = new LinkedList<OggettoOrdinato>();

        for(OggettoOrdinato daAggiungere : carrello.values() ){
            oggettoOrdinatoFacade.create(daAggiungere);
            lista.add(daAggiungere);
        }
        o.setListaOggettiOrdinati(lista);

        float totale =getTotale(sp);
        o.setTotale(totale);
        om.creaOrdine(o);
        Fattura f = new Fattura();
        f.setData(dataOrdine);
        f.setDettaglio("Gli oggetti acquistati sono :" + lista.toString() + "Il prezzo è " + totale);
        this.carrello.clear();
        subTotale= new Float(0.00);
        
        
        
        
        
    }
    
    @Override
    public Integer getQuantitaProdotto(Long idProdotto) {
        OggettoOrdinato oo;
        if ((oo = carrello.get(idProdotto)) == null) {
            throw new IllegalArgumentException();
        }
        return oo.getQuantita();
    }

    @Override
    public Integer getQuantitaProdotto(Prodotto prodotto) {
        return this.getQuantitaProdotto(prodotto.getId());
    }

    @Override
    public Float getTotale(Long idSpedizione) {
        return subTotale+om.cercaPrezzoSpedizione(idSpedizione);
   }
    
     @Override
    public Float getTotale(TipoSpedizione sp) {
        return subTotale+sp.getPrezzo();
   }
    
     @Override
    public Float getSubTotale() {
        return subTotale;
    }

    public void persist(Object object) {
        em.persist(object);
    }

    @Override
    public boolean isEmpty() {
        System.out.println("Il carrello è vuoto ? " + carrello.isEmpty());
        return carrello.isEmpty();
    }
    
    
    
    
} 
    
    
    
    
    

    

   
    
    

