/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.Amministratore;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface AmministratoreFacadeLocal {

    void create(Amministratore amministratore);

    void edit(Amministratore amministratore);

    void remove(Amministratore amministratore);

    Amministratore find(Object id);

    List<Amministratore> findAll();

    List<Amministratore> findRange(int[] range);

    int count();
    
}
