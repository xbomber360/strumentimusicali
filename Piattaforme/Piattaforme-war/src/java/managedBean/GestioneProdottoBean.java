/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package managedBean;

import ejb.manager.CategoriaManagerLocal;
import ejb.manager.MarcaManagerLocal;
import ejb.manager.ProdottoManagerLocal;
import entity.Categoria;
import entity.Marca;
import entity.Prodotto;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author siciliano
 */
public class GestioneProdottoBean {
    @EJB
    private ProdottoManagerLocal prodottoManager;
    @EJB
    private CategoriaManagerLocal categoriaManager;
    @EJB
    private MarcaManagerLocal marcaManager;
    
    private Long id=null;
    private String marca=null;
    private String categoria=null;
    private String nome=null;
    private String urlFoto=null;
    private String descrizione=null;
    private int prezzo=0;
    private int quantità=0;
    

    /**
     * Creates a new instance of GestioneProdottoBean
     */
    public GestioneProdottoBean() {
    }
    
    public List<String> categoriaUpdate(String query){
       // System.out.println("query:"+query );
        List<String> res =categoriaManager.cercaPattern(query); 
        System.out.println(res);
        return res;
    }
    
    public List<String> marcaUpdate(String query){
        List<String> res = marcaManager.cercaPattern(query);
        System.out.println(res);
        return res;
    }
    
    public void cercaProdotto(Long id){
        Prodotto p =prodottoManager.cercaProdottoPerId(id);
        this.id = p.getId();
        marca=p.getMarca().getNome();
        nome=p.getNome();
        urlFoto=p.getFoto();
        descrizione=p.getDescrizione();
        prezzo=p.getPrezzo();
        quantità=p.getQuantita();
        
    }

    public ProdottoManagerLocal getProdottoManager() {
        return prodottoManager;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nome;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getPrezzo() {
        return prezzo;
    }

    public int getQuantità() {
        return quantità;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setPrezzo(int prezzo) {
        this.prezzo = prezzo;
    }

    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    
    public List<Categoria> getCategorie(){
        return categoriaManager.cercaTutto();
    }
    
    public String inserisciProdotto(){
        Marca m = marcaManager.cercaPerNome(marca);
        Categoria c = categoriaManager.cercaPerNome(categoria);
        Prodotto temp = prodottoManager.cercaProdottoPerNome(nome);
        Long idProdotto;
        if(temp!=null){
         idProdotto= temp.getId(); //In caso il prodotto già ci sia aggiorno solo la quantità
        
        if(idProdotto!=null && m!=null && c!=null){
            
            if(temp.getNome().equals(nome)&& temp.getMarca().toString().equals(marca) && temp.getCategoria().toString().equals(categoria)){ //questo controllo serve perchè ci potrebbe essere un prodotto con lo stesso nome ma stessa categoria e marca e va a modificare quello
            prodottoManager.modificaQuantitaProdotto(idProdotto, quantità);
            return "";}
            return""; //il controllo se ci sta è gia in aggiungi prodotto, il prodotto è gia presente , esci dal metodo
            }//if interno
        }//if esterno
        if (m==null){ // se la marca non esiste la creo
            m = new Marca();
            m.setNome(marca);
            marcaManager.creaMarca(m);
        }
        if (c==null){ // se la categoria non esiste la creo
            c = new Categoria();
            c.setNome(categoria);
            categoriaManager.creaCategoria(c);
        }
        
        Prodotto p = new Prodotto();
        p.setNome(nome);
        p.setCategoria(c);
        p.setMarca(m);
        p.setDescrizione(descrizione);
        p.setFoto(urlFoto);
        p.setPrezzo(prezzo);
        p.setQuantita(quantità);
      
        prodottoManager.aggiungiProdotto(p);
        return "";
    }
    
    
    
}
