/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author siciliano
 */

@NamedQueries({
    @NamedQuery(name="tipoSpedizione.cercaPrezzoSpedizionePerId",query="SELECT p.prezzo FROM TipoSpedizione p WHERE p.id=?1"),
    @NamedQuery(name="tipoSpedizione.cercaPrezzoSpedizionePerNome",query="SELECT p.prezzo FROM TipoSpedizione p WHERE p.nome=?1"),
    @NamedQuery(name="tipoSpedizione.cercaTipoSpedizione",query="SELECT tp FROM TipoSpedizione tp WHERE tp.id=?1"),
    

})
@Entity
public class TipoSpedizione implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private float prezzo ;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof TipoSpedizione)) {
            return false;
        }
        TipoSpedizione other = (TipoSpedizione) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
    
    

    @Override
    public String toString() {
        return "entity.TipoSpedizione[ id=" + id + " ]";
    }
    
}
