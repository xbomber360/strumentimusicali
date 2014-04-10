/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Fattura;
import entity.Ordine;
import javax.ejb.Stateless;

/**
 *
 * @author siciliano
 */
@Stateless
public class FatturaManager implements FatturaManagerLocal {

    @Override
    public Fattura generaFattura(Ordine o) {
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

   
    
    
}
