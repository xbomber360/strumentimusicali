/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Spedizione;
import entity.TipoSpedizione;
import javax.ejb.Stateless;

/**
 *
 * @author siciliano
 */
@Stateless
public class SpedizioneManager implements SpedizioneManagerLocal {

    @Override
    public Spedizione generaSpedizione(TipoSpedizione ts) {
        return null;
    }
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public float cercaPrezzoSpedizione(TipoSpedizione tipoSpedizione) {
        return 0.0F;
    }
    
    
    
}
