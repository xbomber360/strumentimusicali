/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Categoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface CategoriaManagerLocal {
    
    void creaCategoria(Categoria cat);

    Categoria cercaPerNome(String nome);

    List<Categoria> cercaTutto();

    void modificaMarchio(Categoria marchio);

    void rimuoviMarchio(Categoria marchio);
}
