/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name="cercaProdottoPerId",query="SELECT p FROM Prodotto p WHERE p.id=?1"),
    @NamedQuery(name="cercaProdottoPerNome",query="SELECT p FROM Prodotto p WHERE p.nome=?1"),
    @NamedQuery(name="cercaTuttiProdotti",query="SELECT p FROM Prodotto p")
})
public class Prodotto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        @Column(nullable = false , unique = true)
        private String nome;
            private int prezzo;
                private String descrizione;
                    private int quantita;
    
    @OneToOne
    private Marca marca;
    @OneToOne
    private Categoria categoria;

    /**
     * Get the value of quantita
     *
     * @return the value of quantita
     */
    public int getQuantita() {
        return quantita;
    }

    /**
     * Set the value of quantita
     *
     * @param quantita new value of quantita
     */
    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }


    /**
     * Get the value of descrizione
     *
     * @return the value of descrizione
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Set the value of descrizione
     *
     * @param descrizione new value of descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    

    /**
     * Get the value of prezzo
     *
     * @return the value of prezzo
     */
    public int getPrezzo() {
        return prezzo;
    }

    /**
     * Set the value of prezzo
     *
     * @param prezzo new value of prezzo
     */
    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
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
        if (!(object instanceof Prodotto)) {
            return false;
        }
        Prodotto other = (Prodotto) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prodotto[ id=" + id + " ]";
    }
    
}
