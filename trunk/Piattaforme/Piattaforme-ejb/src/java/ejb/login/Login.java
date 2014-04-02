/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.login;

import entity.Utente;
import javax.ejb.Stateful;

/**
 *
 * @author maidenfp
 */
@Stateful
public class Login implements LoginLocal {

    @Override
    public Utente accesso(String username, String password) {
        
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
