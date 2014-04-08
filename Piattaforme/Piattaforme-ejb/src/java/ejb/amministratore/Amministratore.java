/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.amministratore;

import facade.AmministratoreFacade;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author maidenfp
 */
@Stateful
public class Amministratore implements AmministratoreLocal {
   
    private Long id;
    private String nome;    

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public void setAmministratore (Amministratore admin) {
        this.id = admin.getID();
        this.nome = admin.getNome();
    }
        
}
