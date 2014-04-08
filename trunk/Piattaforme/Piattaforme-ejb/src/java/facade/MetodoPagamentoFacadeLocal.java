/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package facade;

import entity.MetodoPagamento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface MetodoPagamentoFacadeLocal {

    void create(MetodoPagamento metodoPagamento);

    void edit(MetodoPagamento metodoPagamento);

    void remove(MetodoPagamento metodoPagamento);

    MetodoPagamento find(Object id);

    List<MetodoPagamento> findAll();

    List<MetodoPagamento> findRange(int[] range);

    int count();
    
}
