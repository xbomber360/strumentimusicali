/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.shopping.Carrello;
import ejb.shopping.CarrelloLocal;
import entity.Prodotto;
import entity.TipoSpedizione;
import exception.ClienteNonPresenteException;
import exception.ProdottoNonTrovatoException;
import exception.ProdottoQuantitaException;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author siciliano
 */
@Named(value = "carrelloManagerBean")
@Dependent
public class CarrelloManagerBean implements Serializable{
    
    @EJB
    private CarrelloLocal carrello;

   
    public void setCarrello(GestioneCliente gc){
        this.carrello=gc.getCarrello();
    }
    
    public CarrelloManagerBean() {
        
        
    }
    
    public void aggiungiProdottoAlCarrello(Long idProdotto , int quantita) throws ProdottoNonTrovatoException, ProdottoQuantitaException{
        System.out.println("Ho aggiunto idprodotto: " + idProdotto + " quantità :" + quantita );
        carrello.aggiungiProdottoAlCarrello(idProdotto, quantita);
        
    }
    
    public void rimuoviProdottoDalCarrello(Long idProdotto ){
        carrello.rimuoviProdottoDalCarrello(idProdotto);
        
    }
    
    public void rimuoviQuantitaDalCarrello(Long idProdotto, Integer quantita) {
        carrello.rimuoviQuantitaProdottoDalCarrello(idProdotto, quantita);
    }
    
    public List<Prodotto> getProdotti() {
        System.out.println("I prodotti nel carrello sono " + carrello.getProdotti());
        return carrello.getProdotti();
    }
    
    public Integer getQuantitaProdotto(Prodotto prodotto) {
        return carrello.getQuantitaProdotto(prodotto);
    }
    
    public Float getSubTotale() {
        return carrello.getSubTotale();
    }
    
    public Float getTotale(TipoSpedizione spese) {
        return carrello.getTotale(spese);
    }
    
     public void processaOrdine(Long idCliente, TipoSpedizione spese) throws ClienteNonPresenteException {
         carrello.creaOrdine(idCliente, spese);
     }
    
    
    
    
    
}
