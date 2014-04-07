/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.registrazione;

import entity.Comune;
import java.sql.Date;
import javax.ejb.Local;

/**
 *
 * @author maidenfp
 */
@Local
public interface RegistrazioneLocal {
    
    void registraUtente (String nome, String cognome, String codiceFiscale, Date dataNascita, Comune comune, String via, String username, String password);
}