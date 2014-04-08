/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb.cliente;

import ejb.manager.UtenteManagerLocal;
import ejb.shopping.CarrelloLocal;
import entity.Comune;
import entity.Provincia;
import facade.ComuneFacade;
import facade.ProvinciaFacade;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author maidenfp
 */


@Stateful
public class Cliente implements ClienteLocal {

    @EJB
    private CarrelloLocal carrelloEJB;
    @EJB
    private ComuneFacade comuneFacade;
    @EJB
    private ProvinciaFacade provinciaFacade;
    @EJB
    private UtenteManagerLocal clienteManager;
    
    @PersistenceContext(unitName = "Piattaforme-ejbPU")
    private EntityManager em;
    
    private Long id;
    private String nome; 
    private Long idProvincia;
    private Long idComune;

    @Override
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public void setID(Long id) {
        this.id = id;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void setComune (Comune c) {
        this.setIDComune(c.getId());
    }
    
    @Override
    public Comune getComune() {
        if (idComune != null) {
            return this.comuneFacade.find(idComune);
        }
        return null;
    }

    @Override
    public void setProvincia(Provincia p) {

        this.setIdProvincia(p.getId());
    }

    @Override
    public Provincia getProvincia() {
        if (idProvincia != null) {
            return this.provinciaFacade.find(idProvincia);
        }
        return null;

    }
    
    @Override
    public void setIdProvincia(Long idProvincia) {
        if (provinciaFacade.find(idProvincia) == null) {
            throw new IllegalArgumentException("La provincia selezionata non è presente nel database");
        }
        this.idProvincia = idProvincia;
        resetByProvincia();
    }

    @Override
    public void setIdComune(Long idComune) {
        if (idProvincia == null) {
            throw new IllegalStateException("Bisogna settare prima la provincia");
        }
        this.idComune = idComune;
        resetByComune();
    }
    
   
    }
    
    

    
    
    
    
}
