/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import manager.StatoClienti;

/**
 *
 * @author Lorenzo
 */
@Entity
@DiscriminatorValue(value="C")
@NamedQueries({
@NamedQuery(name = "Utente.isRegistrato", query = "SELECT u FROM Utente u WHERE u.username=?1"),
@NamedQuery(name = "EsisteCliente", query = "SELECT c From Cliente c WHERE c.id=?1"),
@NamedQuery(name = "ClienteEmail", query = "SELECT u FROM Utente u WHERE u.email=?1"),
@NamedQuery(name = "ClienteID", query = "SELECT u FROM Utente u WHERE u.id=?1")
})
public class Cliente extends Utente  {
    
    @Column (nullable = false)
    private String indirizzo_spedizione;
    private StatoClienti stato;
    
    public Cliente() {
        
    }
    
    public void setIndirizzoSpe (String ind) {
        indirizzo_spedizione = ind;
    }
    
    public String getIndirizzoSpe () {
        return indirizzo_spedizione;
    }

    public StatoClienti getStato() {
        return stato;
    }

    public void setStato(StatoClienti stato) {
        this.stato = stato;
    }
   
    
}
