/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import entity.Provincia;
import facade.ProvinciaFacadeLocal;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author maidenfp
 */
public class ProvinciaManaged implements Serializable{

    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    private Provincia provincia;
    private List<Provincia> province;
    /**
     * Creates a new instance of ProvinciaManaged
     */
    public ProvinciaManaged() {
    }

    public ProvinciaFacadeLocal getProvinciaFacade() {
       return provinciaFacade;
    }

     public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
         Provincia provEff=provinciaFacade.find(provincia.getId());
        this.provincia = provEff;
    }

    public List<Provincia> getProvince() {
        return province;
    }

    public void setProvince(List<Provincia> province) {
        this.province = province;
    }
    
    @PostConstruct
   public void init(){
        this.province=provinciaFacade.findAll();
        
   }
    
    
}
