/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.gestoremagazzino;

import javax.ejb.Stateful;

/**
 *
 * @author maidenfp
 */
@Stateful
public class GestoreMagazzino implements GestoreMagazzinoLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
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
}