/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Marca;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface MarcaManagerLocal {
    
    void creaMarca(Marca m);

    Marca cercaPerNome(String nome);

    List<Marca> cercaTutto();

    void modificaMarca (Marca m);

    void rimuoviMarca(Marca m);

    List<String> cercaPattern(String query);
}
