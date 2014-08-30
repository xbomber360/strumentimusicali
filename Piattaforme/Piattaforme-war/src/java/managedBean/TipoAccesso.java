/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import entity.Amministratore;
import entity.GestoreMagazzino;

/**
 *
 * @author siciliano
 */
public class TipoAccesso {

    private Redirect redirect;
    
    public TipoAccesso() {
    }
    
    public String pannelloUtente() {
        
        if (redirect.isAccesso()) {
            
            if (redirect.getUtente() instanceof Amministratore) {
            return "AccessoAmministratore";
            } else if (redirect.getUtente() instanceof GestoreMagazzino) {
                return "AccessoMagazzino";
            }
            return "AccessoCliente";
              
        } else {
            return null;
        }
        
        
        
        
    }
    
}
