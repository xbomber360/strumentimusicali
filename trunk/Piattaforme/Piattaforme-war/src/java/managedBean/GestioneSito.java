/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.amministratore.AmministratoreLocal;
import ejb.gestoremagazzino.GestoreMagazzinoLocal;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author siciliano
 */
public class GestioneSito implements Serializable {

    @EJB
    private AmministratoreLocal amministratore;
    @EJB
    private GestoreMagazzinoLocal gestoremagazzino;
    
    public GestioneSito() {
    }
    
    public String getNomeAmministratore() {
        return amministratore.getNome();
    }
    
    public Long getIDAmministratore() {
        return amministratore.getID();
    }
    
    public String getNomeGestoreMagazzino() {
        return gestoremagazzino.getNome();
    }
    
    public Long getIDGestoreMagazzino() {
        return gestoremagazzino.getID();
    }
    
    public void setNomeAmministratore (String nome) {
        amministratore.setNome(nome);
    }
    
    public void setNomeGestoreMagazzino (String nome) {
        gestoremagazzino.setNome(nome);
    }
    
    public void setIDAmministratore (Long id) {
        amministratore.setID(id);
    }
    
    public void setIDGestoreMagazzino (Long id) {
        gestoremagazzino.setID(id);
    }
}
