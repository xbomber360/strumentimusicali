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
import facade.TipoSpedizioneFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author siciliano
 */
@Named(value = "carrelloManagerBean")
@SessionScoped
public class CarrelloManagerBean implements Serializable{
    
    @EJB
    private TipoSpedizioneFacadeLocal tipoSpedizioneFacade;
    
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
    public boolean isEmpty(){
         
         return  carrello.isEmpty();
     }
    
    
    public Integer getQuantitaProdotto(Prodotto prodotto) {
        return carrello.getQuantitaProdotto(prodotto);
    }
    
    public Float getSubTotale() {
        return carrello.getSubTotale();
    }
    
    public Float getTotale() {
        TipoSpedizione sp = tipoSpedizioneFacade.find(new Long(1));
        return carrello.getTotale(sp);
    }
    
     public String processaOrdine(Long idCliente) throws ClienteNonPresenteException {
        TipoSpedizione sp = tipoSpedizioneFacade.find(new Long(1));
         return carrello.creaOrdine(idCliente, sp);
        
     }
     
     
    
    
    
    
}
