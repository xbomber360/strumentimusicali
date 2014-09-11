/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 *
 * @author Lorenzo
 */
@NamedQueries({
    @NamedQuery(name = "EsisteGestore", query = "SELECT c From GestoreMagazzino c WHERE c.id=?1")})



@Entity
public class GestoreMagazzino extends Utente {
    
    public GestoreMagazzino() {
        
    }
}
