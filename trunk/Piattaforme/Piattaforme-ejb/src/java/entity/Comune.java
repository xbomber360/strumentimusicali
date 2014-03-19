/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Lorenzo
 */
@Entity

@NamedQueries({
@NamedQuery(name="trovaComuniConProvincia", query="SELECT c FROM Comune c WHERE c.provincia=?1"),
@NamedQuery(name="trovaComunePerNome", query="SELECT c FROM Comune c WHERE c.nome=?1"),
@NamedQuery(name="trovaComunePerCap", query="SELECT c FROM Comune c WHERE c.cap=?1"),

})


public class Comune implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        @Column(nullable = false , unique = true)
        private String nome;        
            @Column(nullable = false, unique = true)
            private Integer cap;
                    @OneToOne
                    private Provincia provincia;      
                        @OneToMany(mappedBy = "comune")
                        private List<Utente> listaUtenti;

    /**
     * Get the value of listaUtenti
     *
     * @return the value of listaUtenti
     */
    public List<Utente> getListaUtenti() {
        return listaUtenti;
    }

    /**
     * Set the value of listaUtenti
     *
     * @param listaUtenti new value of listaUtenti
     */
    public void setListaUtenti(List<Utente> listaUtenti) {
        this.listaUtenti = listaUtenti;
    }


    /**
     * Get the value of provincia
     *
     * @return the value of provincia
     */
    public Provincia getProvincia() {
        return provincia;
    }

    /**
     * Set the value of provincia
     *
     * @param provincia new value of provincia
     */
    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }


    /**
     * Get the value of cap
     *
     * @return the value of cap
     */
    public Integer getCap() {
        return cap;
    }

    /**
     * Set the value of cap
     *
     * @param cap new value of cap
     */
    public void setCap(Integer cap) {
        this.cap = cap;
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
        if (!(object instanceof Comune)) {
            return false;
        }
        Comune other = (Comune) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Comune[ id=" + id + " ]";
    }
    
}
