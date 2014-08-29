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
        System.out.println("query:"+query );
        List<String> res =categoriaManager.cercaPattern(query); 
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
        Prodotto p = new Prodotto();
        Marca m = marcaManager.cercaPerNome(marca);
        Categoria c = categoriaManager.cercaPerNome(categoria);
        //TODO da inserire il controllo 
        p.setNome(nome);
        p.setCategoria(c);
        p.setMarca(m);
        p.setDescrizione(descrizione);
        p.setFoto(nome);
        p.setPrezzo(prezzo);
        p.setQuantita(quantità);
        System.out.println(nome);
        System.out.println(marca);
        System.out.println(categoria);
        System.out.println(prezzo);
        
        System.out.println(p);



        
        prodottoManager.aggiungiProdotto(p);
        System.out.println("Prodotto aggiunto");
        return "";
    }
    
    
    
}
