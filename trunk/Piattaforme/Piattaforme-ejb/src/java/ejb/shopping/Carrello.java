/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import classi.OggettoOrdinato;
import ejb.gestoremagazzino.ProductManagerLocal;
import java.io.Serializable;
import java.util.LinkedList;
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
    private ProductManagerLocal pm;
    
    private  LinkedList<OggettoOrdinato> listaOggettiOrdinati;
    
    private Float subTotale; 
 
    @PreDestroy
    protected void preDestroy() {
        svuotaCarrello();
    }

    @PostConstruct
    protected void init() {
        this.listaOggettiOrdinati = new LinkedList<OggettoOrdinato>();
        this.subTotale = new Float(0.00);
    }
    
    @Override
    public void aggiungiProdottoAlCarrello(OggettoOrdinato o) {
        this.listaOggettiOrdinati.add(o);
    }

    @Override
    public void rimuoviProdottoDalCarrello(OggettoOrdinato o) {
        this.listaOggettiOrdinati.remove(o);
    }

    @Override
    public void aggiungiQuantitaProdotto(Long idProdotto , int quantita) {
        
    }

    @Override
    public void rimuoviQuantitaProdotto(Long idProdotto, int quantita) {
    }

    @Override
    public void svuotaCarrello() {
        this.listaOggettiOrdinati.clear();
        subTotale= new Float(0.00);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void creaOrdine(Long idCliente) {
    }
    
    
    
    

    

   
    
    
}
