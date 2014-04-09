/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Prodotto;
import exception.ProdottoNonTrovatoException;
import facade.ProdottoFacade;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author maidenfp
 */
@Stateless
public class ProdottoManager implements ProdottoManagerLocal {
    
    EntityManager em ;
    ProdottoFacade pf ;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean controllaQuantita(Long idProdotto, int quantita) throws ProdottoNonTrovatoException{
        
        Prodotto p = pf.find(idProdotto);
        if(p == null)
            throw new ProdottoNonTrovatoException();
        
        return p.getQuantita() >= quantita;
    }

    @Override
    public Prodotto cercaProdottoPerId(Long idProdotto) {
    }
    
    
    
    
}
