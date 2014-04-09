/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import classi.OggettoOrdinato;
import ejb.manager.ProdottoManagerLocal;
import entity.Spedizione;
import exception.ProdottoNonTrovatoException;
import exception.ProdottoQuantitaException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

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
    
    private Map<Long, OggettoOrdinato> carrello;
    
    private Float subTotale; 
 
    @PreDestroy
    protected void preDestroy() {
        svuotaCarrello();
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
            subTotale+= pm.cercaProdottoPerId(idProdotto).getPrezzo();
            
        }//if
        else
            throw new ProdottoQuantitaException();
        
    }

    @Override
    public void rimuoviProdottoDalCarrello(Long idprodotto) {
        if(carrello.get(idprodotto) == null)
            return;
        OggettoOrdinato o = carrello.remove(idprodotto);
        subTotale+= pm.cercaProdottoPerId(o.getIdProdotto()).getPrezzo();

        
    }

    @Override
    public void aggiungiQuantitaProdotto(Long idProdotto , int quantita) {
        
        
        
        
    }

    @Override
    public void rimuoviQuantitaProdotto(Long idProdotto, int quantita) {
    }

    @Override
    public void svuotaCarrello() {
        this.carrello.clear();
        subTotale= new Float(0.00);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void creaOrdine(Long idCliente) {
    }

    @Override
    public Float getTotale(Spedizione spese) {
   }
    
    
} 
    
    
    
    
    

    

   
    
    
}
