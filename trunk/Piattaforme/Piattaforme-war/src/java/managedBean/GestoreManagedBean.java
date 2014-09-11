/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.gestoremagazzino.GestoreMagazzinoLocal;
import entity.GestoreMagazzino;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Francesco Palumbo
 */
public class GestoreManagedBean {
    
    @EJB
    private GestoreMagazzinoLocal gestoreMagazzinoManager;
    GestoreMagazzino gestore;

    /**
     * Creates a new instance of GestoreManagedBean
     */
    public GestoreManagedBean() {
    }
    
    public List<GestoreMagazzino> getGestore(){
        return gestoreMagazzinoManager.cercaTuttiGestori();
    }
    
    public void rimuoviGestori(GestoreMagazzino gm){
        gestoreMagazzinoManager.rimuoviGestori(gm);
        
    }
}
