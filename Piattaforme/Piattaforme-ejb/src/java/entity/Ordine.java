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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import manager.StatoOrdini;

/**
 *
 * @author Lorenzo
 */
@Entity
@NamedQueries({
    @NamedQuery(name="ordine.cercaOrdinePerId",query="SELECT o FROM Ordine o WHERE o.id=?1"),
    @NamedQuery(name="ordine.cercaOrdiniUtente",query="SELECT o FROM Ordine o WHERE o.utente.id=?1")
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
            @Column (nullable = false)          
            private Date dataOrdine;
            private StatoOrdini stato;
            private float totale;
            @ManyToOne
            TipoSpedizione tipoSpedizione;
            
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

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }

    public TipoSpedizione getTipoSpedizione() {
        return tipoSpedizione;
    }

    public void setTipoSpedizione(TipoSpedizione tipoSpedizione) {
        this.tipoSpedizione = tipoSpedizione;
    }

    
    /**
     * Get the value of listaOggettiOrdinati
     *
     * @return the value of listaOggettiOrdinati
     */
    public List<OggettoOrdinato> getListaOggettiOrdinati() {
        return listaOggettiOrdinati;
    }
    
     public void setListaOggettiOrdinati(List<OggettoOrdinato> carrello) {
        this.listaOggettiOrdinati = carrello;
    }


    /**
     * Set the value of listaOggettiOrdinati
     *
     * @param listaOggettiOrdinati new value of listaOggettiOrdinati
     * @return 
     */
    public Utente getUtente() {
        return utente;
    }

    public void setCliente(Utente utente) {
        this.utente = utente;
    }

  
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
    
    public StatoOrdini getStato() {
        return stato;
    }

    public void setStato(StatoOrdini stato) {
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
