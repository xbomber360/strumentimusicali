/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Spedizione;
import entity.TipoSpedizione;
import javax.ejb.Local;

/**
 *
 * @author siciliano
 */
@Local
public interface SpedizioneManagerLocal {

    Spedizione generaSpedizione(TipoSpedizione sp);

    float cercaPrezzoSpedizione(TipoSpedizione tipoSpedizione);
    
}
