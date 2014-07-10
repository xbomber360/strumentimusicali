/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author siciliano
 */
@Named(value = "carrelloManagerBean")
@Dependent
public class CarrelloManagerBean {

    /**
     * Creates a new instance of CarrelloManagerBean
     */
    public CarrelloManagerBean() {
    }
    
    public void aggiungiProdottoAlCarrello(Long id , int quantita){
        System.out.println("Ho aggiunto idprodotto: " + id + " quantità :" + quantita );
        
    }
    
    
    
}
