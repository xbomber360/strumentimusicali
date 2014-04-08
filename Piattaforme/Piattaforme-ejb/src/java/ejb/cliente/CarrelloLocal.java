/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.cliente;

import entity.Prodotto;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface CarrelloLocal {
    
    void aggiungiProdottoAlCarrello(final Long idProdotto, final Integer quantita);

    void rimuoviProdottoDalCarrello(Long idProdotto);

    void rimuoviQuantitaDalCarrello(Long idProdotto, Integer quantita);

    List<Prodotto> getProdotti();

    Integer getQuantitaProdotto(Long idProdotto);

    Integer getQuantitaProdotto(Prodotto prodotto);

    Float getSubTotale();

    Float getTotale();

    void processaOrdine(Long idCliente);

    void svuotaCarrello();

    boolean isEmpty();

}
