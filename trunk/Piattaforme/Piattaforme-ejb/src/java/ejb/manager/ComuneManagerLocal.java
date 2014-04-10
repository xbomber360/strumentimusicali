/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Comune;
import entity.Provincia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface ComuneManagerLocal {
    
    List<Comune> trovaDaProvincia(Provincia provincia);

    List<Comune> trovaDaIdProvincia(Long idProvincia);

    Comune trovaDaId(Long id);
    
}
