/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package classi;

import java.io.Serializable;

/**
 *
 * @author siciliano
 */
public class OggettoOrdinato implements Serializable,Comparable<OggettoOrdinato>  {
    
    
    private Long idProdotto;
    private int quantita;

    public OggettoOrdinato(Long idProdotto, int quantità) {
        this.idProdotto = idProdotto;
        this.quantita = quantità;
    }

    public OggettoOrdinato() {
    }

    public Long getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(Long idProdotto) {
        this.idProdotto = idProdotto;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.idProdotto != null ? this.idProdotto.hashCode() : 0);
        hash = 61 * hash + this.quantita;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OggettoOrdinato other = (OggettoOrdinato) obj;
        if (this.idProdotto != other.idProdotto && (this.idProdotto == null || !this.idProdotto.equals(other.idProdotto))) {
            return false;
        }
        if (this.quantita != other.quantita) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OggettoOrdinato{" + "idProdotto=" + idProdotto + ", quantit\u00e0=" + quantita + '}';
    }
    
    

    @Override
    public int compareTo(OggettoOrdinato o) {
       return this.idProdotto.compareTo(o.getIdProdotto());
    }
    
}
