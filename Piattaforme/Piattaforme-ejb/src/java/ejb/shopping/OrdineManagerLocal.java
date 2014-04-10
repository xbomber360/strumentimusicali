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

    void creaOrdine(Long idCliente , List<OggettoOrdinato> carrello, Spedizione spedizione);

    void rimuoviOrdine(Ordine o );

    void modificaStatoOrdine(Ordine o, StatoOrdini s);

    void aggiungiSpedizione(Ordine o, Spedizione s);

    void aggiungiFattura(Ordine o, Fattura f);

    void rimuoviFattura(Ordine o);

    void rimuoviSpedizione(Ordine o);

    public void creaFattura(Fattura f);

    public Float cercaPrezzoSpedizione(TipoSpedizione spese);

    
}
