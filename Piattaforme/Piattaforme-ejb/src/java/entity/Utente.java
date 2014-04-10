/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;



/**
 *
 * @author maidenfp
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQueries({
@NamedQuery(name = "cercaUtentePerEmail" , query= "SELECT u FROM Utente u WHERE u.email=?1"  ),
@NamedQuery(name = "cercaUtentePerUsername" , query= "SELECT u FROM Utente u WHERE u.username=?1"  ),
@NamedQuery(name = "cercaUtentePerNomeCognome" , query= "SELECT u FROM Utente u WHERE u.nome=?1 AND u.cognome=?2 "),

})
public class Utente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    private Date nascita;
    @Column(unique = true , nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @ManyToOne
    private Comune comune;
    @OneToMany(mappedBy = "utente")
    private List<Ordine> listaOrdini;
    @Column (nullable = false)
    private String metodopagamento;

    /**
     * Get the value of listaOrdini
     *
     * @return the value of listaOrdini
     */
    public List<Ordine> getListaOrdini() {
        return listaOrdini;
    }

    /**
     * Set the value of listaOrdini
     *
     * @param listaOrdini new value of listaOrdini
     */
    public void setListaOrdini(List<Ordine> listaOrdini) {
        this.listaOrdini = listaOrdini;
    }

    public String getMetodopagamento() {
        return metodopagamento;
    }

    public void setMetodopagamento(String metodopagamento) {
        this.metodopagamento = metodopagamento;
    }


    
    public Comune getComune() {
        return comune;
    }

    /**
     * Set the value of comune
     *
     * @param comune new value of comune
     */
    public void setComune(Comune comune) {
        this.comune = comune;
    }
    
    public Date getNascita() {
        return nascita;
    }

    /**
     * Set the value of nascita
     *
     * @param nascita new value of nascita
     */
    public void setNascita(Date nascita) {
        this.nascita = nascita;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }


    /**
     * Get the value of cognome
     *
     * @return the value of cognome
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Set the value of cognome
     *
     * @param cognome new value of cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set the value of nome
     *
     * @param nome new value of nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }        

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utente)) {
            return false;
        }
        Utente other = (Utente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Utente[ id=" + id + " ]";
    }
    
}