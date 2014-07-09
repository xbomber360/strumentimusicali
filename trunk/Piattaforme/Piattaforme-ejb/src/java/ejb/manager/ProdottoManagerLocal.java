/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Categoria;
import entity.Fattura;
import entity.Marca;
import entity.Prodotto;
import entity.TipoSpedizione;
import exception.ProdottoNonTrovatoException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface ProdottoManagerLocal {

    boolean controllaQuantita(Long idProdotto, int quantita)throws ProdottoNonTrovatoException;

    Prodotto cercaProdottoPerId(Long idProdotto);

    void aggiungiCategoria(Categoria c);

    void aggiungiMarca(Marca m);

    void aggiungiProdotto(Prodotto p, Marca m, Categoria c);

    void modificaQuantitaProdotto(Prodotto p, int quantita);

    void rimuoviCategoria(Categoria c);

    void rimuoviProdotto(Prodotto p);

    List<Prodotto> cercaProdottoPerNome(String parameter);

    List<Prodotto> cercaTuttiProdotti();

    List<Prodotto> cercaProdottiPerMarca(String marca);

    void modificaQuantitaProdottoId(Long idProdotto, int quantita);

    public List<Prodotto> cercaProdottiPerMarcaCategoria(Long idMarca, Long idCategoria);

    
}
