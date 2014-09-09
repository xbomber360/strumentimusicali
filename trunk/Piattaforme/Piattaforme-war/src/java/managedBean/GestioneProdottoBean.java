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
    
    private Long id=new Long(1);
    private String marcaSelezionata=null;
    private String categoriaSelezionata=null;
    private String nome=null;
    private String urlFoto=null;
    private String descrizione=null;
    private int prezzo=0;
    private int quantita=0;
    private int quantitaDaAcquistare=0;
    

    /**
     * OLTRE A GESTIRE I PRODOTTI GESTISCO (get,rimozione,modificia)ANCHE LE CATEGORIE E LE MARCHE SENZA CREARE UN BEAN SPECIFICO PER OGNUNO DI ESSI
     * 
     */
    public GestioneProdottoBean() {
    }
    
    public List<String> prodottoUpdate(String query){
        List<String> res = prodottoManager.cercaPattern(query);
        return res;
    }
    
    public List<String> categoriaUpdate(String query){
        List<String> res =categoriaManager.cercaPattern(query); 
        return res;
    }
    
    public List<String> marcaUpdate(String query){
        List<String> res = marcaManager.cercaPattern(query);
        return res;
    }
    
    public List<Prodotto> getProdotti(){
        return prodottoManager.cercaTuttiProdotti();
    }
    
    public void  Faistampa(){
        System.out.println("ciao");
        
    }
    
    
    
    public void cercaProdotto(Long id){
        Prodotto p =prodottoManager.cercaProdottoPerId(id);
        this.id = p.getId();
        marcaSelezionata=p.getMarca().getNome();
        nome=p.getNome();
        urlFoto=p.getFoto();
        descrizione=p.getDescrizione();
        prezzo=p.getPrezzo();
        quantita=p.getQuantita();
        
    }
    
    public Long cercaIdProdottoPerNome(String nome){
        Prodotto p = prodottoManager.cercaProdottoPerNome(nome);
        if(p==null){
            System.out.println("Il prodotto cercato è null");
            return new Long(403);
        }
        return p.getId();
        
    }
    
    public void rimuoviProdotto(Prodotto p){
        prodottoManager.rimuoviProdotto(p);
        
    }
   
    
    public String inserisciProdotto(){
        Marca m = marcaManager.cercaPerNome(marcaSelezionata);
        Categoria c = categoriaManager.cercaPerNome(categoriaSelezionata);
        Prodotto temp = prodottoManager.cercaProdottoPerNome(nome);
        Long idProdotto;
        if(temp!=null){
         idProdotto= temp.getId(); //In caso il prodotto già ci sia aggiorno solo la quantita
        
        if(idProdotto!=null && m!=null && c!=null){
            
            if(temp.getNome().equals(nome)&& temp.getMarca().toString().equals(marcaSelezionata) && temp.getCategoria().toString().equals(categoriaSelezionata)){ //questo controllo serve perchè ci potrebbe essere un prodotto con lo stesso nome ma stessa categoriaSelezionata e marcaSelezionata e va a modificare quello
            prodottoManager.modificaQuantitaProdotto(idProdotto, quantita);
            return "";}
            return""; //il controllo se ci sta è gia in aggiungi prodotto, il prodotto è gia presente , esci dal metodo
            }//if interno
        }//if esterno
        if (m==null){ // se la marcaSelezionata non esiste la creo
            m = new Marca();
            m.setNome(marcaSelezionata);
            marcaManager.creaMarca(m);
        }
        if (c==null){ // se la categoriaSelezionata non esiste la creo
            c = new Categoria();
            c.setNome(categoriaSelezionata);
            categoriaManager.creaCategoria(c);
        }
        
        Prodotto p = new Prodotto();
        p.setNome(nome);
        p.setCategoria(c);
        p.setMarca(m);
        p.setDescrizione(descrizione);
        p.setFoto(urlFoto);
        p.setPrezzo(prezzo);
        p.setQuantita(quantita);
      
        prodottoManager.aggiungiProdotto(p);
        return "";
    }
    
    public void modificaQuantitaProdotto(Prodotto p , int nuovaQuantita){
        prodottoManager.modificaQuantitaProdotto(p, quantita);
    }
    
    public String settaTuttiParametriDaProdotto(Prodotto p){
        this.id=p.getId();
        this.nome=p.getNome();
        this.marcaSelezionata=p.getMarca().getNome();
        this.categoriaSelezionata=p.getCategoria().getNome();
        this.urlFoto=p.getFoto();
        this.descrizione=p.getDescrizione();
        this.prezzo=p.getPrezzo();
        this.quantita=p.getQuantita();
        return "CreaProdotto";
                
    }
    
    public void inserisciMarca(){
        Marca m = new Marca();
        m.setNome(marcaSelezionata);
        marcaManager.creaMarca(m);
    }
    public List<Marca> getMarche(){
        return marcaManager.cercaTutto();
    }
    
    public void rimuoviMarca(Marca m){
        System.out.println("ciao");
        marcaManager.rimuoviMarca(m);
    }
    
    public void modificaMarca(Marca m){
        marcaManager.modificaMarca(m);
    }
    
    public void inserisciCategoria(){
        Categoria c = new Categoria();
        c.setNome(categoriaSelezionata);
        categoriaManager.creaCategoria(c);
    }
    public List<Categoria> getCategorie(){
        return categoriaManager.cercaTutto();
    }
    
    public void rimuoviCategoria(Categoria c){
        categoriaManager.rimuoviCategoria(c);
    }
    
    public void modificaCategoria(Categoria c){
        categoriaManager.modificaCategoria(c);
    }
    
    
    
     public ProdottoManagerLocal getProdottoManager() {
        return prodottoManager;
    }

    public String getMarcaSelezionata() {
        return marcaSelezionata;
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

    public int getQuantita() {
        return quantita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMarcaSelezionata(String marcaSelezionata) {
        this.marcaSelezionata = marcaSelezionata;
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

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getCategoriaSelezionata() {
        return categoriaSelezionata;
    }

    public void setCategoriaSelezionata(String categoriaSelezionata) {
        this.categoriaSelezionata = categoriaSelezionata;
    }

    public int getQuantitaDaAcquistare() {
        return quantitaDaAcquistare;
    }

    public void setQuantitaDaAcquistare(int quantitaDaAcquistare) {
        this.quantitaDaAcquistare = quantitaDaAcquistare;
    }
    
    public List<String> getListaFoto(){
        return prodottoManager.cercaTutteFotoProdotti();
    }
    
   
    
    
}
