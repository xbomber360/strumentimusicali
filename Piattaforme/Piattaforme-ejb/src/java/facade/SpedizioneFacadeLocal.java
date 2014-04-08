/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.Spedizione;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface SpedizioneFacadeLocal {

    void create(Spedizione spedizione);

    void edit(Spedizione spedizione);

    void remove(Spedizione spedizione);

    Spedizione find(Object id);

    List<Spedizione> findAll();

    List<Spedizione> findRange(int[] range);

    int count();
    
}
