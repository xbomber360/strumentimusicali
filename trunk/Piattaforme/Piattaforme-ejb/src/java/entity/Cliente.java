/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author Lorenzo
 */
@Entity
public class Cliente extends Utente  {
    
    @Column (nullable = false)
    private String indirizzo_spedizione;
    
    public Cliente() {
        
    }
    
    public void setIndirizzoSpe (String ind) {
        indirizzo_spedizione = ind;
    }
    
    public String getIndirizzoSpe () {
        return indirizzo_spedizione;
    }
    
}
