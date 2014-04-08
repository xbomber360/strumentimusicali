/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import ejb.gestoremagazzino.StatoOrdine;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
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
    @NamedQuery(name="cercaOrdinePerId",query="SELECT o FROM Ordine o WHERE o.id=?1"),
    
})
public class Ordine implements Serializable {
    @ManyToOne
    private Utente utente;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        @OneToMany(mappedBy = "ordine")
        private List<OggettoOrdinato> listaOggettiOrdinati;
            @OneToOne
            private Spedizione spedizione;
            @Column (nullable = false)          
            private Date dataOrdine;
            @OneToOne
            private Fattura fattura;
            private StatoOrdine stato;
    /**
     * Get the value of dataOrdine
     *
     * @return the value of dataOrdine
     */
    public Date getDataOrdine() {
        return dataOrdine;
    }

    /**
     * Set the value of dataOrdine
     *
     * @param dataOrdine new value of dataOrdine
     */
    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    /**
     * Get the value of listaOggettiOrdinati
     *
     * @return the value of listaOggettiOrdinati
     */
    public List<OggettoOrdinato> getListaOggettiOrdinati() {
        return listaOggettiOrdinati;
    }

    /**
     * Set the value of listaOggettiOrdinati
     *
     * @param listaOggettiOrdinati new value of listaOggettiOrdinati
     */
    public void setListaOggettiOrdinati(List<OggettoOrdinato> listaOggettiOrdinati) {
        this.listaOggettiOrdinati = listaOggettiOrdinati;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Spedizione getSpedizione() {
        return spedizione;
    }

    public void setSpedizione(Spedizione spedizione) {
        this.spedizione = spedizione;
    }

    public Fattura getFattura() {
        return fattura;
    }

    public void setFattura(Fattura fattura) {
        this.fattura = fattura;
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void setStato(StatoOrdine stato) {
        this.stato = stato;
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
        if (!(object instanceof Ordine)) {
            return false;
        }
        Ordine other = (Ordine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Ordine[ id=" + id + " ]";
    }
    
}
