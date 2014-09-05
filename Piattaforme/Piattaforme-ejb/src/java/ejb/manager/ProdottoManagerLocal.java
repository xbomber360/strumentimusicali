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
import java.util.Set;
import javax.ejb.Local;
import javax.persistence.Query;

/**
 *
 * @author maidenfp
 */
@Local
public interface ProdottoManagerLocal {

    boolean controllaDisponibilita(Long idProdotto, int quantita);

    Prodotto cercaProdottoPerId(Long idProdotto);

    void aggiungiCategoria(Categoria c);

    void aggiungiMarca(Marca m);


    void modificaQuantitaProdotto(Prodotto p, int quantita);
    
     public void modificaQuantitaProdotto(Long idProdotto, int quantita);

    void rimuoviCategoria(Categoria c);

    void rimuoviProdotto(Prodotto p);

    Prodotto cercaProdottoPerNome(String parameter);

    List<Prodotto> cercaTuttiProdotti();

    List<Prodotto> cercaProdottiPerMarca(String marca);


    public List<Prodotto> cercaProdottiPerMarcaCategoria(Long idMarca, Long idCategoria);

    public List<Prodotto> prodottiDaUnSet(Set<Long> codiceBarre);

    public void aggiungiProdotto(Prodotto p);

    List<String> cercaPattern(String query);
    
     public boolean isPresenteProdottoDellaMarca(Marca m);
     
     public boolean isPresenteProdottoDellaCategoria(Categoria c);

    public void aggiungiQuantitaProdotto(Prodotto p, int quantita);

    public void rimuoviQuantitaProdotto(Prodotto p, int quantita);

    public void aggiungiQuantitaProdotto(Long idProdotto, int quantita);

    public void rimuoviQuantitaProdotto(Long idProdotto, int quantita);



   

    
}
