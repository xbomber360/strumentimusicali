/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.cliente;

import ejb.shopping.CarrelloLocal;
import entity.Comune;
import entity.Provincia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface ClienteLocal {
    
    void setComune (Comune c);
    Comune getComune();
    void setProvincia (Provincia p);
    Provincia getProvincia();
    void setNome (String nome);
    void setID ( Long id);
    Long getID();
    String getNome();
    boolean isLogged();
    CarrelloLocal getCarrelloEJB();
    List<entity.Ordine> getOrdiniCliente();
    List<entity.Cliente> cercaTuttiClienti();
    void attivaCliente(entity.Cliente c);
    void bloccaCliente(entity.Cliente c);
    void rimuoviCliente (entity.Cliente c);
    void promuoviCliente (entity.Cliente c);
    void aggiornaGestore (entity.Cliente c);
}
