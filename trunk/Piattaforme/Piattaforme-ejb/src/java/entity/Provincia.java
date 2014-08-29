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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Lorenzo
 */
@Entity
@NamedQuery(name="provincia.findall",query="SELECT p FROM Provincia p ORDER BY p.nome")
public class Provincia implements Serializable, Comparable<Provincia> {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        @Column(nullable = false , unique = true)
        private String nome;
        private String sigla;
                @OneToMany(mappedBy = "provincia")
                private List<Comune> listaComuni;

    /**
     * Get the value of listaComuni
     *
     * @return the value of listaComuni
     */
    public List<Comune> getListaComuni() {
        return listaComuni;
    }

    /**
     * Set the value of listaComuni
     *
     * @param listaComuni new value of listaComuni
     */
    public void setListaComuni(List<Comune> listaComuni) {
        this.listaComuni = listaComuni;
    }


    /**
     * Get the value of nome
     *
     * @return the value of nome
     */
    public String getNome() {
        return nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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
        if (!(object instanceof Provincia)) {
            return false;
        }
        Provincia other = (Provincia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id+","+nome+","+sigla;
    }

    @Override
    public int compareTo(Provincia o) {
        return this.id.compareTo(o.id);
    }
    
}
