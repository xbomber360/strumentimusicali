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

    public List<Categoria> getListaCategoria(){
        return categoriaManager.cercaTutto(); 
    }
    
    public List<Marca> getMarche(Long idCategoria){
         if(idCategoria != null){
             return categoriaManager.getMarcheCategoria(idCategoria);
             
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
    
    public void setCategoria(Long id){
        Categoria nuova = categoriaManager.cercaPerId(id);
        if(nuova!=null){
        c = nuova;}
      
    }
    
    public void setIdMarcaSelezionata(Long id){
        this.idMarcaSelezionata=id;
    }
    
    public Long getIdMarcaSelezionata(){
        if(idMarcaSelezionata!=null)
            return idMarcaSelezionata;
        return (long) -1;
    }
    
    public List<Prodotto> getProdottoMarcaCatagoria(Long idMarca, Long idCategoria) {
    return prodottoManager.cercaProdottiPerMarcaCategoria( new Long(1) ,new Long(1));
    }  
    
}
