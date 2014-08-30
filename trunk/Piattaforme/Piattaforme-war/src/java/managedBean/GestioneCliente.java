/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.cliente.ClienteLocal;
import ejb.shopping.CarrelloLocal;
import ejb.shopping.OrdineManagerLocal;
import entity.Cliente;
import entity.Comune;
import entity.Provincia;
import facade.ProvinciaFacade;
import facade.ProvinciaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author siciliano
 */
public class GestioneCliente implements Serializable {

    @EJB
    private OrdineManagerLocal ordineManager;
    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    @EJB
    private ClienteLocal clienteEJB;
    
    public GestioneCliente() {
    }
    
    public boolean isLogged() {
        return clienteEJB.isLogged();
    }

    public void setId(Long id) {
        clienteEJB.setID(id);
    }// setNome

    public void setNome(String nome) {

        clienteEJB.setNome(nome);
    }// setNome
    
     public String getNome() {

        return clienteEJB.getNome();
    }// getNome

    public Long getId() {

        return clienteEJB.getID();
    }

    public void setComune(Comune c) {
        clienteEJB.setComune(c);
    }

    public Comune getComune() {
        return clienteEJB.getComune();
    }

    public void setProvincia(Provincia p) {
        clienteEJB.setProvincia(p);
    }

    

    public Provincia getProvincia() {
        return clienteEJB.getProvincia();
    }



    public List<Provincia> getProvince() {
        return provinciaFacade.findAll();
    }
    
    public CarrelloLocal getCarrello() {
        return clienteEJB.getCarrelloEJB();
    }

    public String verificaAccesso() {

        if (clienteEJB.getID() != null) {

            return "AccessoCliente";
        }
        return "";
    }// verificaAccesso



    
}
