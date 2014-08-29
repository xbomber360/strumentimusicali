/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Categoria;
import entity.Marca;
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

    void modificaCategoria(Categoria cat);

    void rimuoviCategoria(Categoria cat);

    List<Marca> getMarcheCategoria(Long idCategoria);

    public Categoria cercaPerId(Long id);

    List<String> cercaPattern(String query);
}
