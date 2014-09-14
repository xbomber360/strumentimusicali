/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import entity.Prodotto;
import entity.TipoSpedizione;
import exception.ClienteNonPresenteException;
import exception.ProdottoNonTrovatoException;
import exception.ProdottoQuantitaException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author siciliano
 */
@Local
public interface CarrelloLocal {

    void aggiungiProdottoAlCarrello(Long idProdotto , int quantita)throws ProdottoNonTrovatoException,ProdottoQuantitaException;

    void rimuoviProdottoDalCarrello(Long idProdotto);

    public void aggiungiQuantitaProdottoAlCarrello(Long idProdotto, int quantita);

    public void rimuoviQuantitaProdottoDalCarrello(Long idProdotto, int quantita);

    void svuotaCarrello();

    String creaOrdine(Long idCliente, TipoSpedizione sp)throws ClienteNonPresenteException;

    Float getTotale(TipoSpedizione sp);
    
    public Float getTotale(Long idSpedizione);

    public List<Prodotto> getProdotti();

    public Float getSubTotale();

    public Integer getQuantitaProdotto(Prodotto prodotto);

    public Integer getQuantitaProdotto(Long idProdotto);

    boolean isEmpty();       
    
}
