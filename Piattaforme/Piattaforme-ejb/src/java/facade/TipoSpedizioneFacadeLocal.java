/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.TipoSpedizione;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author siciliano
 */
@Local
public interface TipoSpedizioneFacadeLocal {

    void create(TipoSpedizione tipoSpedizione);

    void edit(TipoSpedizione tipoSpedizione);

    void remove(TipoSpedizione tipoSpedizione);

    TipoSpedizione find(Object id);

    List<TipoSpedizione> findAll();

    List<TipoSpedizione> findRange(int[] range);

    int count();
    
}
