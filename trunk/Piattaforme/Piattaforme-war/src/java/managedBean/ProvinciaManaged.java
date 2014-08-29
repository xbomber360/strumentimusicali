/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.manager.ComuneManagerLocal;
import entity.Comune;
import entity.Provincia;
import facade.ProvinciaFacadeLocal;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author maidenfp
 */
public class ProvinciaManaged implements Serializable{

    @EJB
    private ProvinciaFacadeLocal provinciaFacade;
    @EJB
    private ComuneManagerLocal comuneManager;
    private Provincia provincia;
    private Comune comune;
    private List<Provincia> province;
    private Map<Provincia,List<Comune>> comuniContainer;
    private List<Comune> comuni=new LinkedList<Comune>();
    /**
     * Creates a new instance of ProvinciaManaged
     */
    public ProvinciaManaged() {
    }

    public ProvinciaFacadeLocal getProvinciaFacade() {
       return provinciaFacade;
    }

    public List<Comune> getComuni() {
        return comuni;
    }

    public void handleComuni(){
        if(provincia!=null){
            System.out.println("PROVINCIA VALIDA");
            this.comuni=comuniContainer.get(provincia);
        }else{
            System.out.println("Provincia NULLA");
            this.comuni=new LinkedList<Comune>();
        }
    }

    public Comune getComune() {
        return comune;
    }
    
    public void setComune(Comune comune) {
        Comune comEff=comuneManager.trovaDaId(comune.getId());
        this.comune = comEff;
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
        this.province=provinciaFacade.findAllOrdered();
        comuniContainer=new TreeMap<Provincia, List<Comune>>();
        for(Provincia p:province){
          comuniContainer.put(p, comuneManager.trovaDaProvincia(p));
        }
   }
    
    
}