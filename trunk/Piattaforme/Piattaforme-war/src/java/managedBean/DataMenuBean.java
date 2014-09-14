/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.manager.CategoriaManagerLocal;
import ejb.manager.ProdottoManagerLocal;
import entity.Categoria;
import entity.Marca;
import entity.Prodotto;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 *
 * @author siciliano
 */

public class DataMenuBean {
    @EJB
    private ProdottoManagerLocal prodottoManager;
    @EJB
    private CategoriaManagerLocal categoriaManager;
    
    
    private Categoria c = null; 
    private Long idMarcaSelezionata = null;
    private Long idCategoriaSelezionata = null;

    public List<Categoria> getListaCategoria(){
        return categoriaManager.cercaTutto(); 
    }
    
    public List<Marca> getMarche(Long id){
         if(id != null){
             return categoriaManager.getMarcheCategoria(id);
             
         }
         return new LinkedList<Marca>();
    }
    
    /**
     * Creates a new instance of DataMenuBean
     */
    public DataMenuBean() {
        
        //c= categoriaManager.cercaPerNome("chitarre");
    }
    
    public List<Prodotto>getProdottiCategoria(){
       if(c!=null){
           return c.getListaProdotti();
       }
       return null;
    }
    
    public void setCategoriaSelezionata(Long id){
      idCategoriaSelezionata = id;
        System.out.println("categoria selezionata:"+idCategoriaSelezionata);
    }
    
    public void setMarcaSelezionata(Long id){
        System.out.println("L'id della marca selezionata è " + id);
        this.idMarcaSelezionata=id;
    }
    
    public Long getMarcaSelezionata(){
        if(idMarcaSelezionata!=null)
            return idMarcaSelezionata;
        return (long) -1;
    }
    
    public List<Prodotto> getProdottoMarcaCatagoria() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Long idCategoria = new Long(fc.getExternalContext().getRequestParameterMap().get("idCategoria"));
        String idMarcaS = fc.getExternalContext().getRequestParameterMap().get("idMarca");
        if(idMarcaS == null){
            //TODO scegli una marca a caso e mostrala
            return null;
        }
        Long idMarca=Long.parseLong(idMarcaS);
        System.out.println("idCategoria:"+idCategoria);
        System.out.println("idMarca:"+idMarca);
        return prodottoManager.cercaProdottiPerMarcaCategoria(idMarca, idCategoria);
    }  
    
}
