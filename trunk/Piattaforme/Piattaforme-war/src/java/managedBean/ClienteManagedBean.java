/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.cliente.ClienteLocal;
import entity.Cliente;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Francesco Palumbo
 */
public class ClienteManagedBean {
    
    @EJB
    private ClienteLocal clienteManager;
    Cliente cliente_selezionato;
    

    /**
     * Creates a new instance of CllienteManagedBean
     */
    public ClienteManagedBean() {
    }
    
    public List<Cliente> getCliente(){
        return clienteManager.cercaTuttiClienti();
    }
    
    public void eliminaCliente(Cliente c){
        clienteManager.rimuoviCliente(c);
        
    }
    
    public void attivaCliente (Cliente c) {
        clienteManager.attivaCliente(c);
    }
    
    public void bloccaCliente (Cliente c) {
        clienteManager.bloccaCliente(c);
    }
    
    public void promuoviCliente (Cliente c) {
        clienteManager.promuoviCliente (c);
        clienteManager.aggiornaGestore(c);
    }
    
    
}
