/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Lorenzo
 */
@Entity
@NamedQueries({
    @NamedQuery(name="cercaPerId",query="SELECT s FROM Spedizione s WHERE s.id=?1"),
    
})
public class Spedizione implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
        private Date dataSpedizione;
            private Long tracking;

    /**
     * Get the value of tracking
     *
     * @return the value of tracking
     */
    public Long getTracking() {
        return tracking;
    }

    /**
     * Set the value of tracking
     *
     * @param tracking new value of tracking
     */
    public void setTracking(Long tracking) {
        this.tracking = tracking;
    }


    /**
     * Get the value of dataSpedizione
     *
     * @return the value of dataSpedizione
     */
    public Date getDataSpedizione() {
        return dataSpedizione;
    }

    /**
     * Set the value of dataSpedizione
     *
     * @param dataSpedizione new value of dataSpedizione
     */
    public void setDataSpedizione(Date dataSpedizione) {
        this.dataSpedizione = dataSpedizione;
    }

    
    
    
     @OneToOne(mappedBy = "spedizione")
    private Ordine ordine;
     
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
        if (!(object instanceof Spedizione)) {
            return false;
        }
        Spedizione other = (Spedizione) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Spedizione[ id=" + id + " ]";
    }
    
}
