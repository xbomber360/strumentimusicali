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

/**
 *
 * @author Lorenzo
 */
@Entity
@NamedQueries({
@NamedQuery(name="categoria.cercaPerNome", query="SELECT c FROM Categoria c WHERE c.nome=?1"),
@NamedQuery(name="categoria.cercaPerId",query="SELECT c FROM Categoria c WHERE c.id=?1")

})

public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;
    
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        @Column(unique = true , nullable = false)
        private String nome;
            @OneToMany(mappedBy = "categoria")
            private List<Prodotto> listaProdotti;

    /**
     * Get the value of listaProdotti
     *
     * @return the value of listaProdotti
     */
    public List<Prodotto> getListaProdotti() {
        return listaProdotti;
    }

    /**
     * Set the value of listaProdotti
     *
     * @param listaProdotti new value of listaProdotti
     */
    public void setListaProdotti(List<Prodotto> listaProdotti) {
        this.listaProdotti = listaProdotti;
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
        if (!(object instanceof Categoria)) {
            return false;
        }
        Categoria other = (Categoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }
    
}
