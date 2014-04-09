/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Prodotto;
import exception.ProdottoNonTrovatoException;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface ProdottoManagerLocal {

    boolean controllaQuantita(Long idProdotto, int quantita)throws ProdottoNonTrovatoException;

    Prodotto cercaProdottoPerId(Long idProdotto);
    
}
