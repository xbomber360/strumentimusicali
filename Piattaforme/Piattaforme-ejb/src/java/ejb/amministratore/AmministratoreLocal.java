/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.amministratore;

import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface AmministratoreLocal {
    
    void setNome (String nome);
    void setID ( Long id);
    Long getID();
    String getNome();
    void setAmministratore ( Amministratore admin);
    
}
