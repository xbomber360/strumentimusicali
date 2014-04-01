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
import javax.persistence.OneToOne;

/**
 *
 * @author Lorenzo
 */
@Entity
public class OggettoOrdinato implements Serializable {
    @OneToOne
    private Ordine ordine;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (nullable = false)
    private int quantita;
    @OneToOne
    private Prodotto prodotto_ordinato;
    
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prodotto getProdotto_ordinato() {
        return prodotto_ordinato;
    }

    public void setProdotto_ordinato(Prodotto prodotto_ordinato) {
        this.prodotto_ordinato = prodotto_ordinato;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
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
        if (!(object instanceof OggettoOrdinato)) {
            return false;
        }
        OggettoOrdinato other = (OggettoOrdinato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.OggettoOrdinato[ id=" + id + " ]";
    }
    
}
