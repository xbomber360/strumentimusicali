/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.manager;

import entity.Foto;
import exception.IdFotoNonValido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface FotoManagerLocal {
    
    void creaFoto(Foto f);

    Foto cercaFotoDaId(Long id)throws IdFotoNonValido;

    List<Foto> trovaFotoPerNome(String nome);

    void modificaFoto(Foto foto);

    void eliminaFoto(Foto foto);
    
    List<Foto> getFotoProdotti();

    boolean esisteFoto(Foto foto);

}
