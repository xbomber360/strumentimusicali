/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.shopping;

import entity.Ordine;
import javax.ejb.Local;

/**
 *
 * @author siciliano
 */
@Local
public interface OrdineManagerLocal {

    void creaOrdine(Ordine o);

    public void creaOrdine(Ordine o);
    
}
