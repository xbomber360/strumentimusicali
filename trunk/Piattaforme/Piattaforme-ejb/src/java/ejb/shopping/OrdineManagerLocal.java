/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;


import entity.Ordine;
import entity.TipoSpedizione;
import entity.Utente;
import java.util.List;
import javax.ejb.Local;
import manager.StatoOrdini;

/**
 *
 * @author siciliano
 */
@Local
public interface OrdineManagerLocal {

    void creaOrdine(Ordine o);

    //void creaOrdine(Long idCliente , List<OggettoOrdinato> carrello, TipoSpedizione spedizione);

    void rimuoviOrdine(Ordine o );

    void modificaStatoOrdine(Ordine o, StatoOrdini s);
    
    public Float cercaPrezzoSpedizione(TipoSpedizione spese);

    List<Ordine> cercaTuttiGliOrdini();
    
    public List<Ordine> cercaTuttiGliOrdiniUtente(Utente u);

    
}
