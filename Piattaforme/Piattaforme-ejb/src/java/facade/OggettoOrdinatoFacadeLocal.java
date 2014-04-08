/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.OggettoOrdinato;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface OggettoOrdinatoFacadeLocal {

    void create(OggettoOrdinato oggettoOrdinato);

    void edit(OggettoOrdinato oggettoOrdinato);

    void remove(OggettoOrdinato oggettoOrdinato);

    OggettoOrdinato find(Object id);

    List<OggettoOrdinato> findAll();

    List<OggettoOrdinato> findRange(int[] range);

    int count();
    
}
