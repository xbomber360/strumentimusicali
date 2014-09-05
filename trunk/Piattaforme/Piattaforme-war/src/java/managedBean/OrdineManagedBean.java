/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.shopping.OrdineManagerLocal;
import entity.Ordine;
import entity.Utente;
import java.util.List;
import javax.ejb.EJB;
import manager.StatoOrdini;

/**
 *
 * @author siciliano
 */
public class OrdineManagedBean {
    
    
    @EJB
    private OrdineManagerLocal ordineManager;

    /**
     * Creates a new instance of OrdineManagedBean
     * 
     */
    Ordine ordineSelezionato;
    StatoOrdini statoOrdineSelezionato;
    
    public OrdineManagedBean() {
    }
    
    public List<Ordine> getOrdini(){
        return ordineManager.cercaTuttiGliOrdini();
    }
    
    public void spedisciOrdine(Ordine ordineSelezionato ){
        ordineManager.modificaStatoOrdine(ordineSelezionato, StatoOrdini.Spedito);
        
    }
    
    public void eliminaOrdine(Ordine ordineSelezionato){
        ordineManager.rimuoviOrdine(ordineSelezionato);
        
    }
    
    public List<Ordine> getOrdineUtente(Utente u){
        return ordineManager.cercaTuttiGliOrdiniUtente(u);
    }
    
}
