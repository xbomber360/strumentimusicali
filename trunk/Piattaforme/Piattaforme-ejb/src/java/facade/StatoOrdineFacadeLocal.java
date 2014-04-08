/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.StatoOrdine;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface StatoOrdineFacadeLocal {

    void create(StatoOrdine statoOrdine);

    void edit(StatoOrdine statoOrdine);

    void remove(StatoOrdine statoOrdine);

    StatoOrdine find(Object id);

    List<StatoOrdine> findAll();

    List<StatoOrdine> findRange(int[] range);

    int count();
    
}
