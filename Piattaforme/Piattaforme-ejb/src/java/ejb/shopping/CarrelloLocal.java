/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import classi.OggettoOrdinato;
import entity.Spedizione;
import exception.ProdottoNonTrovatoException;
import exception.ProdottoQuantitaException;
import javax.ejb.Local;

/**
 *
 * @author siciliano
 */
@Local
public interface CarrelloLocal {

    void aggiungiProdottoAlCarrello(Long idProdotto , int quantita)throws ProdottoNonTrovatoException,ProdottoQuantitaException;

    void rimuoviProdottoDalCarrello(Long idProdotto);

    public void aggiungiQuantitaProdotto(Long idProdotto, int quantita);

    public void rimuoviQuantitaProdotto(Long idProdotto, int quantita);

    void svuotaCarrello();

    void creaOrdine(Long idCliente);

    Float getTotale(Spedizione spese);
    
}
