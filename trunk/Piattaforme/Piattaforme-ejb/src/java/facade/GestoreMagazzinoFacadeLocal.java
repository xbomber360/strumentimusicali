/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.GestoreMagazzino;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface GestoreMagazzinoFacadeLocal {

    void create(GestoreMagazzino gestoreMagazzino);

    void edit(GestoreMagazzino gestoreMagazzino);

    void remove(GestoreMagazzino gestoreMagazzino);

    GestoreMagazzino find(Object id);

    List<GestoreMagazzino> findAll();

    List<GestoreMagazzino> findRange(int[] range);

    int count();
    
}
