/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.Fattura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface FatturaFacadeLocal {

    void create(Fattura fattura);

    void edit(Fattura fattura);

    void remove(Fattura fattura);

    Fattura find(Object id);

    List<Fattura> findAll();

    List<Fattura> findRange(int[] range);

    int count();
    
}
