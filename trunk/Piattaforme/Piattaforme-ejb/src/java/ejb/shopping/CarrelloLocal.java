/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import classi.OggettoOrdinato;
import javax.ejb.Local;

/**
 *
 * @author siciliano
 */
@Local
public interface CarrelloLocal {

    void aggiungiProdottoAlCarrello(OggettoOrdinato o);

    void rimuoviProdottoDalCarrello(OggettoOrdinato o);


    public void aggiungiQuantitaProdotto(Long idProdotto, int quantita);

    public void rimuoviQuantitaProdotto(Long idProdotto, int quantita);

    void svuotaCarrello();

    void creaOrdine(Long idCliente);
    
}
