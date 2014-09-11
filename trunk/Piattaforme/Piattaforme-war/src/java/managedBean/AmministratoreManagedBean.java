/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.amministratore.AmministratoreLocal;
import entity.Amministratore;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Francesco Palumbo
 */
public class AmministratoreManagedBean {
    
    @EJB
    private AmministratoreLocal amministratoreManager;
    Amministratore admin;

    /**
     * Creates a new instance of AmministratoreManagedBean
     */
    public AmministratoreManagedBean() {
    }
    
     public List<Amministratore> getAmministratore(){
        return amministratoreManager.cercaTuttiAmministratori();
    }
     
     
    
}
