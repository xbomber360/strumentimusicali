/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.registrazione;

import ejb.manager.ClienteManagerLocal;
import entity.Amministratore;
import entity.Cliente;
import entity.Comune;
import exception.UtenteRegistratoException;
import java.sql.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author maidenfp
 */
@Stateless
public class Registrazione implements RegistrazioneLocal {

    @EJB
    private ClienteManagerLocal clienteManager;
    
    @Override
    public void registraUtente(String nome, String cognome, String codiceFiscale, Date dataNascita, Comune comune, String via, String username, String password) throws UtenteRegistratoException {
        
        Cliente c = new Cliente();
        c.setNome(nome);
        c.setCognome(cognome);
        c.setComune(comune);
        c.setUsername(username);
        c.setPassword(password);
        c.setNascita(dataNascita);
        c.setIndirizzoSpe(via);
        if (clienteManager.ePresente(c)) {
            throw new UtenteRegistratoException();
        }
        clienteManager.creaCliente(c);
      }
    
   
    
    }
