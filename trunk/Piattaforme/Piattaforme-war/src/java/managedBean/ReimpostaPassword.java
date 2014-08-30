/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import java.io.Serializable;
import javax.ejb.EJB;
import ejb.manager.ClienteManagerLocal;

/**
 *
 * @author siciliano
 */
public class ReimpostaPassword implements Serializable {

    @EJB
    private ClienteManagerLocal manager;
    private String password;
    private Long idAccount;
    
    public ReimpostaPassword() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Long idAccount) {
        this.idAccount = idAccount;
    }
    
    
    
}
