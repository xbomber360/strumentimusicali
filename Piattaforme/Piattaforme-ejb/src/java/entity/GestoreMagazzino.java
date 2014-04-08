/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


/**
 *
 * @author Lorenzo
 */
@Entity
@DiscriminatorValue(value="GM")
public class GestoreMagazzino extends Utente {
    
    public GestoreMagazzino() {
        
    }
}
