/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.manager.ComuneManagerLocal;
import ejb.registrazione.RegistrazioneLocal;
import entity.Amministratore;
import entity.Comune;
import exception.UtenteRegistratoException;
import java.sql.Date;
import javax.ejb.EJB;

/**
 *
 * @author maidenfp
 */
public class Registrazione {
    @EJB
    private ComuneManagerLocal comuneManager;
    
    @EJB
    private RegistrazioneLocal registrazione;
    
    private String nome;
    private String cognome;
    private String codiceFiscale;
    private String codicePostale;
    private String numeroTelefono;
    private Date dataNascita;
    private String email;
    private String username;
    private String password;
    private String via;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodicePostale() {
        return codicePostale;
    }

    public void setCodicePostale(String codicePostale) {
        this.codicePostale = codicePostale;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    /**
     * Creates a new instance of Registrazione
     */
    public Registrazione() {
    }
    
    public String registra(ProvinciaManaged provComSel) {
       
        try {
            Comune c = comuneManager.trovaDaId(provComSel.getComune().getId());
            registrazione.registraUtente(nome, cognome, codiceFiscale, dataNascita, c , via, username, password, email);
        } catch (UtenteRegistratoException ex) {
          return "RegistrazioneNonAvvenuta";
        }       
        return "SuccessoRegistrazione";
    }
    
    public void registraAmministratore () {
        
        
    }
    
  
    
}
